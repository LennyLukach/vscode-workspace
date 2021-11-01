#include <iostream>
#include <sstream>

using namespace std;

void other() {

    cout << "Hi! I am an AI, what is your name?" << endl;

    string name;
    cin >> name;

    cout << name << ", that is a great name!" << endl << "And if you dont mind me asking, how old are you?" << endl;

    string ageStr;
    cin >> ageStr;
    int ageInt;
    stringstream(ageStr) >> ageInt;
    cout << "You are " << ageInt << " years old? Thats pretty old ngl." << endl;
    
}

int main() {

    other();
    return 0;
}