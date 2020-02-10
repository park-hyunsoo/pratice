# 1. 생각나는 대로 풀기 O(N^2)
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        for(int i=0; i <phone_book.length; i++){
            for(int j=i+1; j<phone_book.length; j++){                
                if(phone_book[i].length() <= phone_book[j].length())
                {
                    if(phone_book[i].equals(phone_book[j].substring(0, phone_book[i].length())))
                        return false;
                }
                else
                {
                    if(phone_book[j].equals(phone_book[i].substring(0, phone_book[j].length())))
                        return false;
                }
            }
        }
        return answer;
    }
}

2. 해시라는 문제의 태그를 사용해서 풀기

import java.util.LinkedList;
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        HashTable ht = new HashTable(phone_book.length);
        for(String phone : phone_book){
            ht.put(phone);    
        }
               
        for(int i=0; i<phone_book.length; i++){
            for(int j=1; j < phone_book[i].length(); j++){
                if(ht.get(phone_book[i].substring(0,j))!=null){
                    return false;
                } 
            }
        }
        
        return answer;
    }
}


class HashTable{

    LinkedList<String>[] data;
    public HashTable(int size){
        this.data = new LinkedList[size];
    }
    
    int getHashCode(String key){
        return key.length();
    }
    int convertToIndex(int hashcode){
        return hashcode % data.length;
    }
    
    String searchKey(LinkedList<String> list, String key){
        if(list==null) return null;
        for(String node : list){
            if(node.equals(key)){
                return node;
            }
        }
        return null;
    }
    
    public void put(String key){
        int hashcode = getHashCode(key);
        int index = convertToIndex(hashcode);

        LinkedList<String> list = data[index];
        if(list == null){
            list = new LinkedList<String>();
            data[index]=list;
        }
        list.add(key);
    }
    public String get(String key){
        int hashcode = getHashCode(key);
        int index = convertToIndex(hashcode);
        LinkedList<String> list = data[index];
        String node = searchKey(list, key);
        return node;
    }
}
