#!/data/data/com.termux/files/usr/bin/bash
set -e
command -v java >/dev/null || { echo 'Java is required'; exit 1; }
command -v mvn >/dev/null || { echo 'Maven is required. Install with: pkg install maven'; exit 1; }
java -version
mvn -B clean package -DskipTests
