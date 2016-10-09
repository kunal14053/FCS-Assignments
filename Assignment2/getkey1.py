from Crypto.Cipher import DES
import enchant



def dictionary(string):
	wordList = string.split(" ")
	d = enchant.Dict("en_US");	
	for x in wordList:
		if(not d.check(x)):
			return False;
	return True;	

def only_letters(string):
	wordList = string.split(" ")
	for x in wordList:
		if(not all(letter.isalpha() for letter in x)):
			return False;
	return True;


cipher_text=b"\xc5\x81\x97~\xb4\x0b:U\x13^\x9c\xb2:\xedcC\xe5\n\xab\xb2\xbas\xbe/\r\xa8\x00'\x87\x91Ch\xb8\x060\xfb\xf8V\xf7)\x1d\xfb\x12\xe7\x16\xf0\x12\x1dQ\x99Gs`\xf5qZjQL\xe1\x1f\xfd\x90E";


result = open("result.txt", 'w');
	
for i in range(0,99999999):
	Key = str(i);
	size=len(Key);
	for j in range(0,8-size):
		Key="0"+Key;
	
	des = DES.new(Key, DES.MODE_ECB);
	decrypted_pt = des.decrypt(cipher_text);
	
	print decrypted_pt	
	
	if (only_letters(str(decrypted_pt)) and dictionary(str(decrypted_pt))):
		result.write("Text: "+decrypted_pt+" Key: "+Key+'\n');	

result.close();





