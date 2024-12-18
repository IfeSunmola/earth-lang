# Script to generate antlr code from the grammar file
# Run from root like: ./scripts/antlr.sh
# No longer needed since antlr is not being used but just leaving it here for
# future reference.

# Must have antlr4 in your path/aliased

antlr_dir="./src/antlr"
lexer="${antlr_dir}/EarthLexer.g4"
parser="${antlr_dir}/EarthParser.g4"

# check if first argument passed is "clean"

if [ "$1" == "clean" ]; then
  rm -rf $antlr_dir/*.java
  rm -rf $antlr_dir/*.tokens
  rm -rf $antlr_dir/*.interp
  exit
fi

antlr4 \
  -o "." \
  -package "antlr" \
  -no-listener \
  -visitor \
  -lib $antlr_dir \
  $lexer $parser

