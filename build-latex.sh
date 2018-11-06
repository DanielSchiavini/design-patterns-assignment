#!/bin/bash
mkdir output 2>/dev/null

cd Report
pdflatex -output-directory=../output final-assignment-report.tex
cd ..

echo "Report built to output/final-assignment-report.pdf"
