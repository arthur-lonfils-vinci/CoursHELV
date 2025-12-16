#!/usr/bin/env python3
import requests
import os
from pathlib import Path
import getpass

BASE_URL = "http://fileserver.rogue-sentinels.io:8080/api/v1/download?file="

HEADERS = {
    "User-Agent": "Mozilla/5.0 (X11; Linux x86_64; rv:128.0) Gecko/20100101 Firefox/128.0",
    "Accept": "*/*",
    "Accept-Language": "en-US,en;q=0.5",
    "Referer": "http://fileserver.rogue-sentinels.io:8080/secure",
    "Connection": "keep-alive",
    "Pragma": "no-cache",
    "Cache-Control": "no-cache"
}

def download_zip(file_name, save_path, token):
    cookies = {"token": token}
    url = BASE_URL + file_name

    print(f"➡️  Downloading {file_name}")

    try:
        response = requests.get(url, headers=HEADERS, cookies=cookies, timeout=20)

        # Check MIME type to confirm it's a ZIP
        content_type = response.headers.get("Content-Type", "")
        if "zip" not in content_type and "octet-stream" not in content_type:
            print(f"❌ Not a ZIP file (server returned {content_type})")
            bad_path = f"{save_path}.html"
            with open(bad_path, "wb") as f:
                f.write(response.content)
            print(f"   Saved error page: {bad_path}")
            return

        # Save valid ZIP
        with open(save_path, "wb") as f:
            f.write(response.content)
        print(f"✔️  Saved: {save_path}")

    except Exception as e:
        print(f"❌ Error downloading {file_name}: {e}")


def main():
    # Ask for JWT token at runtime
    print("Before, downloading ZIP files, please authenticate on the fileserver.")
    print("http://fileserver.rogue-sentinels.io:8080/secure")
    print("Username: security-admin@web2000-corp.com")
    print("Password: password")
    print("You can get your JWT token from the browser's developer tools (Application -> Cookies).")
    
    # hide token when typing
    token = input("Enter JWT token: ").strip()

    if not token.strip():
        print("❌ No token provided. Exiting.")
        return

    base_dir = Path("./zip/raw_zips")
    base_dir.mkdir(parents=True, exist_ok=True)

    start = 124
    end   = 350

    for idx in range(start, end + 1):
        file_name = f"backup_2001_{idx}.zip"
        save_path = base_dir / file_name
        download_zip(file_name, save_path, token)

    print("\n✅ All downloads attempted.\n")


if __name__ == "__main__":
    main()
