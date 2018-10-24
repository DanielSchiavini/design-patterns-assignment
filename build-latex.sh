#!/bin/bash
mkdir output 2>/dev/null

pdflatex -output-directory=output \
    Report/final-assignment-report.tex

echo "Report built to output/final-assignment-report.pdf"

