#include <sstream>  
#include <Windows.h>  
#include "MainWindow.h"

//��һ�޶���������һ����GUID�ִ�����������������������ظ�  
static const char * CLASS_NAME = "{198CEAB2-AD78-4e0d3-B099-247639080CB0}";

/************************************************************************
�ص����������������յ��κ�Windows��Ϣʱ������
************************************************************************/
/*LRESULT CALLBACK onMainWndMessage(HWND wnd, UINT msg, WPARAM wParam, LPARAM lParam) {
	
}*/

/************************************************************************
�Ǽ��Լ��Ĵ�����
************************************************************************/
/*
bool registerMyClass() {
	WNDCLASSEX  wce = { 0 };
	wce.cbSize = sizeof(wce);
	wce.style = CS_VREDRAW | CS_HREDRAW;
	wce.lpfnWndProc = &onMainWndMessage;  //ָ���ص�����  
	wce.hInstance = GetModuleHandle(0);
	wce.hIcon = LoadIcon(0, IDI_WINLOGO);
	wce.hCursor = LoadCursor(0, IDC_ARROW);
	wce.hbrBackground = reinterpret_cast<HBRUSH>(COLOR_BTNFACE + 1);
	wce.lpszClassName = CLASS_NAME; //��һ�޶�������  
	wce.hIconSm = wce.hIcon;
	return 0 != RegisterClassEx(&wce);
}
*/
/************************************************************************
��������ʾ������
************************************************************************/
/*
bool createMyWindow(int cmdShow) {
	HWND mainWnd = CreateWindowEx(0, CLASS_NAME, "Demo", WS_OVERLAPPEDWINDOW,
		CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT,
		0, 0, GetModuleHandle(0), 0);
	if (0 != mainWnd) {
		ShowWindow(mainWnd, cmdShow);
		UpdateWindow(mainWnd);
		return true;
	}
	else {
		return false;
	}
}
*/
/************************************************************************
��Ϣѭ��
************************************************************************/
int messageLoop() {
	MSG msg;
	while (GetMessage(&msg, 0, 0, 0)) {
		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}
	return static_cast<int>(msg.wParam);
}

/************************************************************************
WinMain���������
************************************************************************/
int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE, char *, int cmdShow) {
	/*if (registerMyClass() && createMyWindow(cmdShow)) {
		return messageLoop();
	}
	else {
		char msg[MAX_PATH];
		sprintf(msg, "����������ʧ�ܣ�������룺%d", GetLastError());
		MessageBox(0, msg, 0, MB_OK | MB_ICONSTOP);
		return 0;
	}*/

	MainWindow mainWnd(hInstance, 800, 800, "������");
	mainWnd.CreateTheWindow();
	mainWnd.WndMessageLoop();
}
