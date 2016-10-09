from Crypto.Cipher import DES
import enchant
import re

def dictionary(string):
	wordList = re.sub("[^\w]", " ",string).split()
	d = enchant.Dict("en_US");	
	for x in wordList:
		if(d.check(x)==False):
			return False;
	return True;			

def only_letters(string):
	wordList = re.sub("[^\w]", " ",string).split()
	for x in wordList:
		if(all(letter.isalpha() for letter in x)==False):
			return False;
	return True;

cipher_text1=b"\xc5\x81\x97~\xb4\x0b:U\x13^\x9c\xb2:\xedcC\xe5\n\xab\xb2\xbas\xbe/\r\xa8\x00'\x87\x91Ch\xb8\x060\xfb\xf8V\xf7)\x1d\xfb\x12\xe7\x16\xf0\x12\x1dQ\x99Gs`\xf5qZjQL\xe1\x1f\xfd\x90E";

cipher_text2=b'\xc5\x81\x97~\xb4\x0b:U\x13^\x9c\xb2:\xedcC\xe5\n\xab\xb2\xbas\xbe/\r\xa8\x00\'\x87\x91Ch\xb8\x060\xfb\xf8V\xf7)\x1d\xfb\x12\xe7\x16\xf0\x12\x1dQ\x99Gs`\xf5qZjQL\xe1\x1f\xfd\x90E';


result1 = open("result1.txt", 'w');
result2 = open("result2.txt", 'w');
result3 = open("result3.txt", 'w');

result4 = open("result4.txt", 'w');
result5 = open("result5.txt", 'w');
result6 = open("result6.txt", 'w');

result7 = open("result7.txt", 'w');
result8 = open("result8.txt", 'w');
result9 = open("result9.txt", 'w');



for i in range(0,99999999):
	Key = str(i);
	size=len(Key);
	for j in range(0,8-size):
		Key="0"+Key;
	
	des = DES.new(Key, DES.MODE_ECB);
	
	decrypted_pt = des.decrypt(str(cipher_text1));
	
	if(dictionary(decrypted_pt)):
		result1.write("Text: "+decrypted_pt+" Key: "+Key+'\n');
	if(only_letters(decrypted_pt)):
		result2.write("Text: "+decrypted_pt+" Key: "+Key+'\n');
	if (only_letters(decrypted_pt) and dictionary(decrypted_pt)):	
		result3.write("Text: "+decrypted_pt+" Key: "+Key+'\n');
	
	decrypted_pt1 = des.decrypt(str(cipher_text2));
	
	if(dictionary(decrypted_pt1)):
		result4.write("Text: "+decrypted_pt1+" Key: "+Key+'\n');
	if(only_letters(decrypted_pt1)):
		result5.write("Text: "+decrypted_pt1+" Key: "+Key+'\n');
	if (only_letters(decrypted_pt1) and dictionary(decrypted_pt1)):	
		result6.write("Text: "+decrypted_pt1+" Key: "+Key+'\n');

	decrypted_pt3 = des.decrypt(cipher_text1);
	
	if(dictionary(decrypted_pt3)):
		result4.write("Text: "+decrypted_pt3+" Key: "+Key+'\n');
	if(only_letters(decrypted_pt3)):
		result5.write("Text: "+decrypted_pt3+" Key: "+Key+'\n');
	if (only_letters(decrypted_pt3) and dictionary(decrypted_pt3)):	
		result6.write("Text: "+decrypted_pt3+" Key: "+Key+'\n');




result1.close();
result2.close();
result3.close();
result4.close();
result5.close();
result6.close();
result7.close();
result8.close();
result9.close();




