## The Gradle Plugin That Screams When The Build Failed

Imagine, that you have to work one of your less talented colleague's code who just left after lunch ( _because work for losers_ ). You are working so hard you can't even blink. After several hours of agony, finally you could extend the whole shitstorm what they call codebase one goddamn line ( _and at least 10 lines of comment why you should never do it_ ). You just open a terminal hit `gradle build` and Boom, __BUILD FAILED__. Your face became red, even the guy next to you start to call 911 with eyes large and dark with fear, but you'd only yell Fuuuuuuuu...

But finally! Thanks for the 21st century technology, it can be automated!

[See how it works](https://pozo.github.io/swear)

### Boring stuff

Install this amazing game changer plugin into your precious local repository

    ./gradlew clean build publishToMavenLocal
    
Remove every comments except the `soundFiles` part from `swear-example/build.gradle`

Do something silly in `Application.kt` and run 

    ./gradlew clean build
    
And voilà.

### Boring customisation stuff

Alternatively you can put your custom `.wav` file under your project's root directory and list it in the `build.gradle` with the help of `swear.soundFiles` (see the `swear-example/build.gradle` )

### Fun staff which inspired this boring stuff

Inspired by Michael Reeves' [pimped Roomba](https://www.youtube.com/watch?v=mvz3LRK263E&feature=youtu.be&t=181).

The default sound files are from [this mighty video](https://www.youtube.com/watch?v=6aK78w-st2U).

### Legal stuff

I'm a butterfly