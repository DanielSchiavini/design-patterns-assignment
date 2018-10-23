#!/bin/bash
docker run --rm --interactive --tty \
    --user="$(id -u):$(id -g)" \
    --network=none \
    --volume "${PWD}":/data \
    "${LATEX_IMAGE}" \
    pdflatex Report/final-assignment-report.tex
