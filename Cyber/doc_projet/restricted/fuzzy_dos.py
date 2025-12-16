#!/usr/bin/env python3
import requests
import time

def test_all_combinations():
    url = "http://restricted.rogue-sentinels.io:8080/calculate"
    
    print("🔍 Testing ALL POST combinations...")
    print(f"📊 Testing range: 0 to 999999 for x, y, z")
    print("=" * 60)
    
    total_combinations = 1000000 * 1000000 * 1000000  # 1e18 - trop énorme!
    print(f"ℹ️  Total possible combinations: {total_combinations:.0e}")
    print("⚠️  This would take forever... Testing systematically instead")
    print("=" * 60)
    
    # Stratégie de test systématique mais réalisable
    test_count = 0
    successful_tests = 0
    error_found = False
    problematic_value = None
    
    print("🧪 Testing diagonal values (x=y=z)...")
    for i in range(0, 1000000):
        data = {"x": i, "y": i, "z": i}
        try:
            response = requests.post(url, data=data, timeout=5)
            test_count += 1
            
            if response.status_code == 200:
                successful_tests += 1
                # Afficher progression tous les 1000 tests
                if i % 1000 == 0:
                    print(f"✅ Progress: {i}/999999 -> {response.text.strip()}")
            else:
                print(f"🚨 ERROR at {i},{i},{i} -> HTTP {response.status_code}")
                print(f"💥 Response: {response.text}")
                error_found = True
                problematic_value = (i, i, i)
                break
                
            time.sleep(0.01)  # Petit délai pour éviter de surcharger
            
        except Exception as e:
            print(f"💥 SERVER CRASH at {i},{i},{i}: {e}")
            error_found = True
            problematic_value = (i, i, i)
            break
    
    if not error_found:
        print("\n🔍 Testing x=0 with all y,z combinations...")
        for y in range(0, 1000000, 1000):  # Step de 1000 pour accélérer
            for z in range(0, 1000000, 1000):
                data = {"x": 0, "y": y, "z": z}
                try:
                    response = requests.post(url, data=data, timeout=5)
                    test_count += 1
                    
                    if response.status_code == 200:
                        successful_tests += 1
                    else:
                        print(f"🚨 ERROR at 0,{y},{z} -> HTTP {response.status_code}")
                        print(f"💥 Response: {response.text}")
                        error_found = True
                        problematic_value = (0, y, z)
                        break
                        
                    time.sleep(0.01)
                    
                except Exception as e:
                    print(f"💥 SERVER CRASH at 0,{y},{z}: {e}")
                    error_found = True
                    problematic_value = (0, y, z)
                    break
            
            if error_found:
                break
    
    if not error_found:
        print("\n🔍 Testing edge value combinations...")
        edge_values = [0, 1, 999998, 999999]
        for x in edge_values:
            for y in edge_values:
                for z in edge_values:
                    data = {"x": x, "y": y, "z": z}
                    try:
                        response = requests.post(url, data=data, timeout=5)
                        test_count += 1
                        
                        if response.status_code == 200:
                            successful_tests += 1
                            print(f"✅ {x},{y},{z} -> {response.text.strip()}")
                        else:
                            print(f"🚨 ERROR at {x},{y},{z} -> HTTP {response.status_code}")
                            print(f"💥 Response: {response.text}")
                            error_found = True
                            problematic_value = (x, y, z)
                            break
                            
                        time.sleep(0.01)
                        
                    except Exception as e:
                        print(f"💥 SERVER CRASH at {x},{y},{z}: {e}")
                        error_found = True
                        problematic_value = (x, y, z)
                        break
                
                if error_found:
                    break
            if error_found:
                break
    
    print("\n" + "=" * 60)
    print("📊 FINAL TEST SUMMARY")
    print("=" * 60)
    print(f"Total tests performed: {test_count}")
    print(f"Successful responses: {successful_tests}")
    
    if error_found and problematic_value:
        print(f"🚨 ERROR DETECTED!")
        print(f"💥 Problematic value: x={problematic_value[0]}, y={problematic_value[1]}, z={problematic_value[2]}")
    else:
        print("✅ No errors detected in tested combinations")
    
    # Test final du serveur
    try:
        final_test = requests.post(url, data={"x": 1, "y": 1, "z": 1}, timeout=5)
        print(f"Final server status: {'✅ UP' if final_test.status_code == 200 else '❌ DOWN'}")
    except:
        print("Final server status: ❌ SERVER CRASHED")

if __name__ == "__main__":
    test_all_combinations()
