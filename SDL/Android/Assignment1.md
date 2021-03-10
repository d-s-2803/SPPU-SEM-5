# Download Install and Configure Android Studio on Linux/windows platform.

### Install Android Studio

First, be sure you download the latest version of [Android Studio](https://developer.android.com/studio).

### Windows

To install Android Studio on Windows, proceed as follows:

If you downloaded an .exe file (recommended), double-click to launch it.
If you downloaded a .zip file, unpack the ZIP, copy the android-studio folder into your Program Files folder, and then open the android-studio > bin folder and launch studio64.exe (for 64-bit machines) or studio.exe (for 32-bit machines).

Follow the setup wizard in Android Studio and install any SDK packages that it recommends.
That's it. The following [video](https://developer.android.com/studio/videos/studio-install-windows.mp4) shows each step of the setup procedure when using the recommended .exe download.

As new tools and other APIs become available, Android Studio tells you with a pop-up, or you can check for updates by clicking Help > Check for Update.

### Linux

To install Android Studio on Linux, proceed as follows:

Unpack the .zip file you downloaded to an appropriate location for your applications, such as within /usr/local/ for your user profile, or /opt/ for shared users.
If you're using a 64-bit version of Linux, make sure you first install the required libraries for 64-bit machines.

To launch Android Studio, open a terminal, navigate to the android-studio/bin/ directory, and execute studio.sh.
Select whether you want to import previous Android Studio settings or not, then click OK.
The Android Studio Setup Wizard guides you through the rest of the setup, which includes downloading Android SDK components that are required for development.

Required libraries for 64-bit machines
If you are running a 64-bit version of Ubuntu, you need to install some 32-bit libraries with the following command: 

`sudo apt-get install libc6:i386 libncurses5:i386 libstdc++6:i386 lib32z1 libbz2-1.0:i386`

If you are running 64-bit Fedora, the command is:

`sudo yum install zlib.i686 ncurses-libs.i686 bzip2-libs.i686`

That's it. The following [video](https://developer.android.com/studio/videos/studio-install-linux.mp4) shows each step of the recommended setup procedure.

As new tools and other APIs become available, Android Studio tells you with a pop-up, or you can check for updates by clicking Help > Check for Update.
