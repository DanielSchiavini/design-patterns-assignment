#!/bin/bash
mkdir output 2>/dev/null

export LATEX_IMAGE="blang/latex:ubuntu"

docker pull ${LATEX_IMAGE}

docker run --rm --interactive --tty \
    --network=none \
    --volume "${PWD}":/data \
    ${LATEX_IMAGE} \
    ./build-latex.sh
