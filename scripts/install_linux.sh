#!/bin/bash

install_dir="./earth-lang"
temp_dir=$(systemd-path temporary)

jre_source="https://github.com/IfeSunmola/test-repo/raw/refs/heads/main/jre_23_linux_x64.tar.xz"
jre_name="${jre_source##*/}" # all characters after the last slash
actual_jre_name="earth-jre"

bin_source="https://github.com/IfeSunmola/test-repo/raw/refs/heads/main/ignore_linux_x64"
bin_name="${bin_source##*/}"
actual_bin_name="earth"

# Download the runtime
echo "Downloading runtime from $jre_source"
wget -q --show-progress -P "$temp_dir" "$jre_source"
echo "Download complete."

# Extract the runtime
echo "Extracting runtime ..."
tar -xf "$temp_dir/$jre_name" -C "$temp_dir"
echo "Runtime extraction done."

# Download the binary
echo "Downloading compiler binary from $bin_source"
wget -q --show-progress -P "$temp_dir" "$bin_source"
echo "Download complete."

# Cleanup, make executable
echo "Wrapping up ..."

rm "$temp_dir/$jre_name" # remove the archive

mkdir -p "$install_dir"

jre_name="${jre_name%.tar.xz}" # remove the .tar.xz extension

# mv from temp to install dir
mv "$temp_dir/$jre_name" "$install_dir"
# rename to the actual name that will be used in the compiler binary
mv "$install_dir/$jre_name" "$install_dir/$actual_jre_name"

mv "$temp_dir/$bin_name" "$install_dir/$actual_bin_name"
chmod +x "$install_dir/$actual_bin_name" # make the binary executable

echo "Installation complete."
echo "Earth compiler has been installed to $install_dir"
echo "To run a program, cd into $install_dir and run ./$actual_bin_name <path-to-earth-file>"
