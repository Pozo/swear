package com.github.pozo

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logging
import org.slf4j.Logger
import java.io.BufferedInputStream
import java.io.InputStream
import java.util.concurrent.CountDownLatch
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.LineEvent

open class SwearPlugin : Plugin<Project> {

    private val logger: Logger = Logging.getLogger(SwearPlugin::class.java)

    private val defaultSoundFiles = listOf("/fuck_long.wav", "/fuck_short.wav")

    override fun apply(project: Project) {
        val extension = project.extensions.run {
            create("swear", SwearExtension::class.java)
        }

        project.gradle.buildFinished {
            if (it.failure != null) {
                if (extension.enabled) {
                    val soundFileAsStream = readSoundFile(extension, project)
                    val bufferedInputStream = BufferedInputStream(soundFileAsStream)
                    val audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream)

                    playSound(audioInputStream)
                } else {
                    logger.debug("plugin is disabled")
                }
            } else {
                logger.debug("build was successful")
            }
        }
    }

    private fun readSoundFile(extension: SwearExtension, project: Project): InputStream? {
        return if (extension.soundFiles.isNotEmpty()) {
            val soundFile = extension.soundFiles.random()
            project.file(soundFile).inputStream()
        } else {
            val soundFile = defaultSoundFiles.random()
            SwearPlugin::class.java.getResourceAsStream(soundFile)
        }
    }

    private fun playSound(soundFileAsStream: AudioInputStream) {
        val sync = CountDownLatch(1)

        val clip = AudioSystem.getClip()
        clip.addLineListener { lineEvent ->
            if (lineEvent.type === LineEvent.Type.STOP) {
                sync.countDown()
            }
        }
        clip.open(soundFileAsStream)
        clip.start()

        sync.await()
    }
}