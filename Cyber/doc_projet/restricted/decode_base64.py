#!/usr/bin/env python3
import base64

def decode_base64():
    encoded_string = input("Enter base64 string to decode: ").strip()
    
    try:
        # Add padding if needed
        padding = 4 - len(encoded_string) % 4
        if padding != 4:
            encoded_string += '=' * padding
        
        # Decode the base64 string
        decoded_bytes = base64.b64decode(encoded_string)
        decoded_string = decoded_bytes.decode('utf-8')
        
        print(f"Decoded result: {decoded_string}")
        
    except Exception as e:
        print(f"Error decoding: {e}")

if __name__ == "__main__":
    decode_base64()
