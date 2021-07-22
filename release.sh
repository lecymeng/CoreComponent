#!/usr/bin/env sh

./gradlew :core:clean :core:build :core:publish --no-daemon --no-parallel --stacktrace && \
./gradlew :ktx:clean :ktx:build :ktx:publish --no-daemon --no-parallel --stacktrace && \

if ! grep -q -- "VERSION_NAME=.*-SNAPSHOT" gradle.properties;
then
./gradlew closeAndReleaseRepository
else
echo "Nothing else to do for a snapshot"
fi