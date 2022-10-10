#include <graphics.h>

int main()
{
    int gd = DETECT, gm;
    initgrahp(&gd, &gm, (char*)"");

    line(100, 150, 200, 300);

    getch();
    closegraph();
    return 0;
}