#!/usr/bin/env python3
import argparse
import zipfile
import os
import concurrent.futures
from pathlib import Path
import time

def brute_force_zip(zip_path, output_base_dir):
    """Brute force a single ZIP file with 4-digit PINs"""
    zip_name = Path(zip_path).stem  # backup_2001_124
    success_file = f"{output_base_dir}/successful_zips.txt"
    
    print(f" Attempting: {zip_name}")
    
    for pin in range(1000, 10000):
        password = str(pin)
        
        try:
            with zipfile.ZipFile(zip_path) as zip_file:
                # Create unique output directory for this ZIP
                output_dir = f"{output_base_dir}/{zip_name}_PIN_{password}"
                os.makedirs(output_dir, exist_ok=True)
                
                # Try to extract with current PIN
                zip_file.extractall(path=output_dir, pwd=password.encode())
                
                # If we get here, password is correct!
                print(f"✅ SUCCESS: {zip_name} -> PIN: {password}")
                
                # Log successful extraction
                with open(success_file, "a") as log:
                    log.write(f"{zip_name}: PIN {password}\n")
                
                # Analyze extracted files
                analyze_extracted_files(output_dir, zip_name, password)
                
                return password
                
        except (RuntimeError, zipfile.BadZipFile):
            # Wrong password - continue to next
            continue
        except Exception as e:
            # Other errors (file might be corrupted or different encryption)
            continue
    
    print(f"❌ Failed: {zip_name} (no 4-digit PIN found)")
    return None

def analyze_extracted_files(extract_dir, zip_name, password):
    """Analyze files in extracted directory for flags and useful content"""
    flag_file = f"{Path(extract_dir).parent}/found_flags.txt"
    
    for root, dirs, files in os.walk(extract_dir):
        for file in files:
            file_path = os.path.join(root, file)
            
            try:
                with open(file_path, 'r', encoding='utf-8', errors='ignore') as f:
                    content = f.read()
                    
                    # Look for flags
                    if 'FLAG' in content:
                        print(f"🚩 FLAG FOUND in {zip_name}/{file}:")
                        print(f"   PIN: {password}")
                        print(f"   Content: {content.strip()}")
                        
                        # Log the flag
                        with open(flag_file, "a") as flag_log:
                            flag_log.write(f"ZIP: {zip_name}\n")
                            flag_log.write(f"PIN: {password}\n")
                            flag_log.write(f"File: {file}\n")
                            flag_log.write(f"Flag: {content.strip()}\n")
                            flag_log.write("-" * 50 + "\n")
                    
                    # Look for SQL files specifically
                    if file.endswith('.sql'):
                        print(f"📊 SQL file in {zip_name}: {file}")
                        
                    # Look for interesting file types
                    if any(ext in file.lower() for ext in ['.txt', '.conf', '.config', '.env']):
                        if len(content.strip()) > 0:
                            print(f"📄 Found {file} in {zip_name} (size: {len(content)} chars)")
                            
            except Exception as e:
                # Can't read file (might be binary)
                pass

def main():
    parser = argparse.ArgumentParser(description='Download backup ZIP files')
    parser.add_argument('--skip', action='store_true', 
                       help='Only download backup_2001_322.zip')
    args = parser.parse_args()
    
    base_dir = "./zip"
    raw_zips_dir = f"{base_dir}/raw_zips"
    extracted_dir = f"{base_dir}/extracted"
    
    # Create directories
    Path(extracted_dir).mkdir(exist_ok=True)
    
    # Get all ZIP files
    zip_files = [f for f in os.listdir(raw_zips_dir) if f.endswith('.zip')]
    zip_files.sort()  # Sort for organized processing
    
    print(f"🎯 Starting brute force on {len(zip_files)} ZIP files")
    print(f" Input: {raw_zips_dir}")
    print(f" Output: {extracted_dir}")
    print("=" * 60)
    
    successful = 0
    start_time = time.time()
    
    if args.skip:
        zip_files = ["backup_2001_322.zip"]
        print("⚠️  Skipping to only process backup_2001_322.zip")
        zip_path = f"{raw_zips_dir}/backup_2001_322.zip"
        result = brute_force_zip(zip_path, extracted_dir)
        if result:
            successful += 1
    else:
        # Process ZIP files with threading for speed
        with concurrent.futures.ThreadPoolExecutor(max_workers=4) as executor:
            futures = []
            
            for zip_file in zip_files:
                zip_path = f"{raw_zips_dir}/{zip_file}"
                future = executor.submit(brute_force_zip, zip_path, extracted_dir)
                futures.append(future)
            
            # Wait for all to complete
            for future in concurrent.futures.as_completed(futures):
                result = future.result()
                if result:
                    successful += 1
    
    end_time = time.time()
    total_time = end_time - start_time
    
    print("\n" + "=" * 60)
    print("📊 BRUTE FORCE SUMMARY")
    print("=" * 60)
    print(f"Total ZIP files processed: {len(zip_files)}")
    print(f"Successfully cracked: {successful}")
    print(f"Failed: {len(zip_files) - successful}")
    print(f"Time taken: {total_time:.2f} seconds")
    print(f"Average time per file: {total_time/len(zip_files):.2f} seconds")
    
    # Show summary of findings
    flag_file = f"{extracted_dir}/found_flags.txt"
    success_file = f"{extracted_dir}/successful_zips.txt"
    
    if os.path.exists(flag_file):
        print(f"\n🚩 Flags found in: {flag_file}")
        with open(flag_file, 'r') as f:
            print(f"Flag content:\n{f.read()}")
    
    if os.path.exists(success_file):
        print(f"\n✅ Successful extractions logged in: {success_file}")
        with open(success_file, 'r') as f:
            successful_files = f.read().strip().split('\n')
            print(f"Cracked {len(successful_files)} files with 4-digit PINs")

if __name__ == "__main__":
    main()
