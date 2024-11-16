# Script to generate antlr code from the grammar file
# Run from root like: ./scripts/antlr.sh

antlr_dir="./src/antlr"
lexer="${antlr_dir}/MoneyLexer.g4"
parser="${antlr_dir}/MoneyParser.g4"

antlr4 \
  -o "." \
  -package "antlr" \
  -no-listener \
  -visitor \
  -lib $antlr_dir \
  $lexer $parser