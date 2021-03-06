#!/bin/bash
mkdir output 2>/dev/null

cd Report
# run first to find references
pdflatex -interaction=nonstopmode \
    -output-directory=../output \
    final-assignment-report.tex

# run again to fix references
pdflatex -interaction=nonstopmode \
    -output-directory=../output \
    final-assignment-report.tex
cd ..

echo "Report built to output/final-assignment-report.pdf"
