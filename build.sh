#!/bin/bash
set -x
docker run --rm -it ${TAG} \
	--user="$(id -u):$(id -g)" \
	--network=none \
    --volume "${PWD}":/data \
    "${LATEX_IMAGE}" \
	pdflatex Report/final-assignment-report.tex
