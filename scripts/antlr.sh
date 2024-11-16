# Script to generate antlr code from the grammar file
# Run from root like: ./scripts/antlr.sh

antlr_dir="./src/antlr"
lexer="${antlr_dir}/MoneyLexer.g4"
parser="${antlr_dir}/MoneyParser.g4"

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

