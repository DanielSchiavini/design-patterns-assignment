#!/bin/bash
mkdir output 2>/dev/null

docker run --rm --interactive --tty \
    --user="$(id -u):$(id -g)" \
    --network=none \
    --volume "${PWD}":/data \
    "${LATEX_IMAGE}" \
    ./build-latex.sh
