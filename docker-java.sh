#!/bin/bash
mkdir output 2>/dev/null

export JAVA_IMAGE="frekele/ant"

docker run --rm --interactive --tty \
    --workdir=/data \
    --volume "${PWD}/Jabberpoint":/data \
    --volume "${PWD}/output":/output \
    "${JAVA_IMAGE}" \
    ant "$@"
