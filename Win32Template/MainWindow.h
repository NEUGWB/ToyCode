#pragma once
#include <Windows.h>
//用于创建窗口，封装一些全局变量
class MainWindow
{
public:
	MainWindow(HINSTANCE hIns, int w, int h, const char *name);
	virtual ~MainWindow();

	void MoveCenter();

	void CreateTheWindow();

	virtual LRESULT SelfWndProc(HWND wnd, UINT msg, WPARAM wParam, LPARAM lParam);

	DWORD WndMessageLoop();
private:
	static LRESULT CALLBACK DefaultProc(HWND wnd, UINT msg, WPARAM wParam, LPARAM lParam);
	void GenClassName();
private:
	char	m_className[128];
	char	m_wndName[128];

	HINSTANCE	m_hInstance;
	HWND	m_hWnd;
	WNDPROC	m_wndProc;

	int		m_width;
	int		m_height;
};
