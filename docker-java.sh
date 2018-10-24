#!/bin/bash
mkdir output 2>/dev/null

docker run --rm --interactive --tty \
    --user="$(id -u):$(id -g)" \
    --network=none \
    --workdir=/data \
    --volume "${PWD}":/data \
    "${JAVA_IMAGE}" \
    ./build-java.sh

