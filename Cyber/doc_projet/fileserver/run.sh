#!/usr/bin/env bash

set -e

echo "[*] Setting up directory structure..."
mkdir -p zip/raw_zips
mkdir -p zip/extracted

echo "[*] Downloading ZIP files..."
python3 download_zips.py

echo "[*] Running brute-force extraction..."
python3 unzip.py --skip
