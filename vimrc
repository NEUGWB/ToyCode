set hlsearch
set nu
set backspace=2
set autoindent
set ruler
set showmode
set bg=dark
syntax enable
colorscheme molokai
nmap <F10> :NERDTreeToggle<CR>
nmap <F8> :TagbarToggle<CR>
set ts=4
set expandtab

set nocompatible

"设置文件的代码形式 utf8
set encoding=utf-8
set termencoding=utf-8
set fileencoding=utf-8
set fileencodings=ucs-bom,utf-8,chinese,cp936

"vim提示信息乱码的解决
language messages zh_CN.utf-8

filetype on
filetype plugin indent on
"colorscheme evening	"配色方案
colorscheme molokai
set helplang=cn		"设置中文帮助
set history=500		"保留历史记录
set guifont=Monaco:h10	"设置字体为Monaco，大小10
set tabstop=4		"设置tab的跳数
set expandtab
set backspace=2 	"设置退格键可用
set nu 		"设置显示行号
"set nowrap 		"设置不自动换行
set linebreak 		"整词换行，与自动换行搭配使用
"set list 		"显示制表符
"set autochdir 		"自动设置当前目录为正在编辑的目录
set hidden 		"自动隐藏没有保存的缓冲区，切换buffer时不给出保存当前buffer的提示
set scrolloff=5 	"在光标接近底端或顶端时，自动下滚或上滚
"Toggle Menu and Toolbar 	"隐藏菜单栏和工具栏
"set guioptions-=m
"set guioptions-=T
set showtabline=2 	"设置显是显示标签栏
set autoread 		"设置当文件在外部被修改，自动更新该文件
set tabstop=4
set softtabstop=4
set shiftwidth=4
set expandtab
"set mouse=a 		"设置在任何模式下鼠标都可用
set nobackup 		"设置不生成备份文件
"set go=				"不要图形按钮
"set guioptions-=T           " 隐藏工具栏
"set guioptions-=m           " 隐藏菜单栏

"===========================
"查找/替换相关的设置
"===========================
set hlsearch "高亮显示查找结果
set incsearch "增量查找
set ignorecase
set smartcase

"===========================
"状态栏的设置
"===========================
set statusline=[%F]%y%r%m%*%=[Line:%l/%L,Column:%c][%p%%] "显示文件名：总行数，总的字符数
set ruler "在编辑过程中，在右下角显示光标位置的状态行

"===========================
"代码设置
"===========================
syntax enable "打开语法高亮
syntax on "打开语法高亮
set showmatch "设置匹配模式，相当于括号匹配
set smartindent "智能对齐
"set shiftwidth=4 "换行时，交错使用4个空格
set autoindent "设置自动对齐
set ai! "设置自动缩进
"set cursorcolumn "启用光标列
set cursorline "设置高亮当前行(这里我注释了)
"hi CursorLine   cterm=NONE ctermbg=darkred ctermfg=white guibg=darkred guifg=white
set guicursor+=a:blinkon0 "设置光标不闪烁
hi MatchParen cterm=bold ctermbg=none ctermfg=magenta

let g:clang_complete_copen=1
let g:clang_periodic_quickfix=1
let g:clang_snippets=1
let g:clang_close_preview=1
let g:clang_use_library=1
let g:clang_user_options='-stdlib=libc++ -std=c++11 -I/usr/include -I/usr/lib/llvm-7/lib/clang/7.0.1/include -I/usr/include/x86_64-linux-gnu -I/usr/local/include'
nmap s <Plug>(easymotion-prefix)

nmap <Space>m <Plug>(quickhl-manual-this)
xmap <Space>m <Plug>(quickhl-manual-this)
nmap <F9>     <Plug>(quickhl-manual-toggle)
xmap <F9>     <Plug>(quickhl-manual-toggle)

nmap <Space>M <Plug>(quickhl-manual-reset)
xmap <Space>M <Plug>(quickhl-manual-reset)

nmap <Space>j <Plug>(quickhl-cword-toggle)

nmap <Space>] <Plug>(quickhl-tag-toggle)

call plug#begin('~/.vim/plugged')
Plug 'junegunn/vim-easy-align'
Plug 'https://github.com/junegunn/vim-github-dashboard.git'
Plug 'scrooloose/nerdtree', { 'on':  'NERDTreeToggle' }
Plug 'junegunn/fzf', { 'dir': '~/.fzf', 'do': './install --all' }
Plug 'junegunn/fzf.vim'
Plug 'easymotion/vim-easymotion'
Plug 't9md/vim-quickhl'
Plug 'majutsushi/tagbar'
Plug 'Shougo/echodoc.vim'
Plug 'jiangmiao/auto-pairs'
Plug 'vim-scripts/autoproto.vim'
call plug#end()
