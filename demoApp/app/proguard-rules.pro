# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/a.baskakov/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#App
-keep public class com.propellerads.sample.** { public *; }

# PropellerAds
-keep public class com.propellerads.sdk.common.** { public *; }
-keepattributes EnclosingMethod

# Admob
-keep class com.google.android.gms.ads.** { *; }
-keep public class com.google.ads.mediation.* { public *; }

# Legacy
-keep class org.apache.http.** { public *; }
-dontwarn org.apache.http.**
-dontwarn android.net.http.**

# Google Play Services library 9.0.0 only
-dontwarn android.security.NetworkSecurityPolicy
-keep public @com.google.android.gms.common.util.DynamiteApi class * { *; }

# support-v4
-keep class android.support.v4.app.Fragment { *; }
-keep class android.support.v4.app.FragmentActivity { *; }
-keep class android.support.v4.app.FragmentManager { *; }
-keep class android.support.v4.app.FragmentTransaction { *; }
-keep class android.support.v4.content.LocalBroadcastManager { *; }
-keep class android.support.v4.util.LruCache { *; }
-keep class android.support.v4.view.PagerAdapter { *; }
-keep class android.support.v4.view.ViewPager { *; }
-keep class android.support.v4.content.ContextCompat { *; }

# support-v7-recyclerview
-keep class android.support.v7.widget.RecyclerView { *; }
-keep class android.support.v7.widget.LinearLayoutManager { *; }
