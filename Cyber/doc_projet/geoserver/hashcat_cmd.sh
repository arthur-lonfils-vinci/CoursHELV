#!/bin/bash
hashcat -m 1400 -a 0 hash.txt ../wordlists/leaked_passwords.txt