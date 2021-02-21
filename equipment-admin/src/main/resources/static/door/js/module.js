var mod = (function (win, doc) {
    /*
      jQuery轮播/切换
      */
    function Switchs(opt) { return new Switchs.prototype.init(opt); };
    Switchs.prototype = {
        init: function (opt) {
            this.container = $(opt.container);
            this.list = this.container.find(opt.list);
            this.lis = this.list.find("li");
            this.btn = this.container.find(opt.btn) || null;
            this.arrow = this.container.find(opt.arrow).find("span") || null;
            this.type = opt.type || "default";
            this.btnEvent = opt.btnEvent || "click";
            this.auto = opt.auto || false;
            this.time = opt.time || 5000;
            this.speed = opt.speed || 800;
            this.switchs();
        },
        switchs: function () {
            var _this = this;
            this.arrFn = [];
            this.timer = null;
            this.i = 0;
            this.old = 0;
            this.len = this.lis.length;
            this.w = this.lis.eq(0).width();
            this.guge = com.ifBrowser("chrome");
            this.arrFn["default"] = function () { _this.defaults(); };
            this.arrFn["easing"] = function () { _this.easing(); };
            this.arrFn["fade"] = function () { _this.fade(); };
            if (this.type == "easing") {
                this.len += 1;
                this.list.append("<li>" + this.lis.eq(0).html() + "</li>");
                this.list.width(this.w * this.len);
            }
            if (this.type == "fade") { this.lis.css({ "position": "absolute", "opacity": "0", "z-index": "1" }); this.lis.eq(0).css({ "opacity": "1", "z-index": "2" }); }
            if (this.btn.length) {
                this.btn.bind(_this.btnEvent, function () {
                    if ($(this).hasClass("active")) return;
                    _this.i = $(this).index();
                    _this.btnClassName();
                    _this.arrFn[_this.type]();
                });
            }
            if (this.arrow.length) {
                this.arrow.eq(0).bind("mousedown", function (e) {
                    if (_this.type == "easing") {
                        _this.i == 0 ? ($(this).addClass("no")) : (_this.i--, $(this).removeClass("no").addClass("on"));
                    }
                    else {
                        _this.i == 0 ? _this.i = _this.len - 1 : _this.i--;
                    }
                    _this.arrFn[_this.type]();
                    _this.btnClassName();
                    return false;
                });
                this.arrow.eq(1).bind("mousedown", function () {
                    if (_this.type == "easing") {
                        _this.arrow.eq(0).removeClass("no").addClass("on");
                    }
                    _this.autoPlay();
                    return false;
                });
            }
            if (this.auto) {
                setTimeout(function () {
                    _this.openTimer();
                }, 200);
                $(win).bind("focus", function () {
                    _this.openTimer();
                });
                this.container.bind("mouseover", function () {
                    _this.closeTimer();
                });
                this.container.bind("mouseout", function () {
                    _this.openTimer();
                });
            }
        },
        btnClassName: function () {
            if (this.btn.length) {
                this.btn.eq(this.i).addClass("active").siblings().removeClass("active");
            }
        },
        autoPlay: function () {
            this.i++;
            var img = this.lis.eq(this.i).find("img");
            if (img.length) {
                for (var i = 0; i < img.length; i++) {
                    if (img.eq(i).attr("src") == undefined) {
                        img.eq(i).attr("src", img.eq(i).attr("data-src"));
                    };
                };
            }
            this.arrFn[this.type]();
            this.btnClassName();
        },
        openTimer: function () {
            var _this = this;
            clearInterval(this.timer);
            this.timer = setTimeout(function () {
                _this.autoPlay();
                _this.openTimer();               
            }, _this.time);
        },
        closeTimer: function () {
            clearInterval(this.timer);
        },
        defaults: function () {
            this.i %= this.len;
            this.lis.eq(this.i).show();
            this.lis.eq(this.old).hide();
            this.old = this.i;
        },
        easing: function () {
            var _this = this;
            _this.i %= _this.len + 1;
            if (_this.i == _this.len) {
                this.list.css({ "left": "0", "-webkit-transition": "0", "-webkit-transform": "translate3d(-" + 0 + "px, 0, 0)" });
                this.i = 1;
            }
            setTimeout(function () {
                if (_this.guge) {
                    _this.list.css({ "-webkit-transition": ".5s", "-webkit-transform": "translate3d(-" + _this.w * _this.i + "px, 0, 0)" });
                }
                else {
                    _this.list.stop().animate({ "left": -_this.w * _this.i });
                }
            }, 50);
        },
        fade: function () {
            var _this = this;
            this.i %= this.len;
            if (this.guge) {
                this.lis.eq(this.i).css({ "opacity": "1", "z-index": "2" });
                this.lis.eq(this.old).css({ "opacity": "0", "z-index": "1" });
            }
            else {
                this.lis.eq(this.i).animate({ "opacity": "1", "z-index": "2" }, { queue: false, duration: _this.speed });
                this.lis.eq(this.old).animate({ "opacity": "0", "z-index": "1" }, { queue: false, duration: _this.speed });
            }
            this.old = this.i;
        }
    };
    Switchs.prototype.init.prototype = Switchs.prototype;
    /*
        原生拖拽
            使用方法：
            mod.Drag({
                downEle: document.getElementById('title'),  （downEle的作用是给元素绑定鼠标按下的事件（downEle.onmousedown），参数：原生dom元素）必须
                moveEle: document.getElementById('box3'),（moveEle的作用是鼠标按下后，指定需要移动的元素（moveEle.onmousemove），如果不填，则downEle充当需要移动的那个元素。参数：原生dom元素）可选
                type: true (是否限制范围，参数true限制，false不限制，默认不限制) 可选
            });
    */
    function Drag(opt) { return new Drag.prototype.init(opt); };
    Drag.prototype = {
        init: function (opt) {
            this.downEle = opt.downEle || opt.obj;
            this.moveEle = opt.moveEle || opt.downEle;
            this.type = opt.type || false;
            this.down();
        },
        down: function () {
            var _this = this;
            this.downEle.onmousedown = function (e) {
                var e = e || event;
                com.prevent(e);
                _this.x = e.clientX - _this.moveEle.offsetLeft, _this.y = e.clientY - _this.moveEle.offsetTop;
                if (this.setCapture) {
                    this.onmousemove = function (e) {
                        _this.move(e);
                    };
                    this.setCapture();
                    this.onmouseup = _this.up;
                }
                else {
                    doc.onmousemove = function (e) {
                        _this.move(e);
                    };
                    doc.onmouseup = _this.up;
                }
                return false;
            };
        },
        move: function (e) {
            var e = e || event, l = e.clientX - this.x, t = e.clientY - this.y;
            if(this.type){
                var w = doc.documentElement.clientWidth, h = doc.documentElement.clientHeight;
                l = l < 0 ? 0 : l > w - this.moveEle.offsetWidth ? w - this.moveEle.offsetWidth : l;
                t = t < 0 ? 0 : t > h - this.moveEle.offsetHeight ? h - this.moveEle.offsetHeight : t;
            }
            this.moveEle.style.left = l + 'px';
            this.moveEle.style.top = t + 'px';
        },
        up: function () {
            this.onmouseup = null;
            this.onmousemove = null;
            if (this.releaseCapture) {
                this.releaseCapture();
            }
        }
    };
    Drag.prototype.init.prototype = Drag.prototype;
    /*
        原生自定义滚动条
            使用方法： 
            mod.ScrollBar({
                container: document.getElementById('box'),（最外层元素，参数：原生dom元素）必须
                target: document.getElementById('center')（滚动的目标元素，参数：原生dom元素）必须
            });
            滚动条是JS生成的，他们的class为：
                custom_scroll（滚动条背景），
                custom_scroll_bar（滚动条）
            需要自己去定义CSS样式
    */
    function ScrollBar(opt) { return new ScrollBar.prototype.init(opt) };
    ScrollBar.prototype = {
        init: function (opt) {
            this.container = opt.container;
            this.target = opt.target;
            this.gap = opt.gap || 20;
            this.ifNewScrollBar();
        },
        createScrollBar: function () {//创建滚动条
            this.scroll_outer = doc.createElement('div');
            this.scroll_bar = doc.createElement('div');
            this.scroll_outer.className = 'custom_scroll';
            this.scroll_bar.className = 'custom_scroll_bar';
            this.container.appendChild(this.scroll_outer);
            this.scroll_outer.appendChild(this.scroll_bar);
        },
        scrollDrag: function () {//添加滚动条拖拽
            var _this = this;
            function barDrag() { };
            barDrag.prototype = new Drag({ downEle: this.scroll_bar });
            barDrag.prototype.move = function (e) {
                var e = e || event;
                var t = e.clientY - this.y;
                _this.setTop(t);
            };
        },
        setTop: function (t) {//移动的位置
            var h = this.scroll_outer.offsetHeight - this.scroll_bar.offsetHeight;
            t = t < 0 ? 0 : t > h ? h : t;
            var scale = t / h;
            this.scroll_bar.style.top = t + 'px';
            this.scroll_bar.style.bottom = 'auto';
            this.target.style.bottom = 'auto';
            this.target.style.top = (this.scroll_outer.offsetHeight - this.target.offsetHeight) * scale + 'px';
        },
        mouseEvent: function (obj, Fn) {//鼠标滚轮事件
            if (obj.addEventListener) {
                obj.onmousewheel === undefined ? obj.addEventListener('DOMMouseScroll', Fn, false) : obj.addEventListener('mousewheel', Fn, false);
            }
            else {
                obj.attachEvent('onmousewheel', Fn);
            }
            //DOMMouseScroll 火狐
            //mousewheel     谷歌 
            //onmousewheel   IE
        },
        mouseScroll: function () {//鼠标滚轮功能
            var _this = this;
            var t = 100;
            this.mouseEvent(this.container, function (e) {
                com.stopDefault(e);
                var val = e.wheelDelta || e.detail;//e.detail只有火狐识别,  e.wheelDelta除了火狐都能识别
                if (val == 120 || val == -3) {//向上
                    _this.setTop(_this.scroll_bar.offsetTop - _this.gap);
                }
                else {//向下
                    _this.setTop(_this.scroll_bar.offsetTop + _this.gap);
                }
            });
        },
        clickScroll: function () {
            var _this = this;
            this.scroll_outer.onmousedown = function (e) {
                var e = e || event, gap = _this.container.offsetTop - (doc.body.scrollTop || doc.documentElement.scrollTop), t = e.clientY - (gap < 0 ? 0 : gap);
                _this.setTop(t);
                com.prevent(e);
            };
        },
        ifNewScrollBar: function () {//是否需要创建滚动条
            var ch = this.container.offsetHeight;
            var sh = this.target.offsetHeight;
            if (sh > ch) {
                this.createScrollBar();
                this.scrollDrag();
                this.mouseScroll();
                this.clickScroll();
                this.target.style.top = "0";
                this.scroll_bar.style.cssText = "top:0;height:" + ch / sh * 100 + "%";
            }
        }
    };
    ScrollBar.prototype.init.prototype = ScrollBar.prototype;

    var rTop = function (tpl) {
        var b = $(tpl || "<a title='返回顶部' class='rTop'></a>").appendTo($("body")).bind("click", function () {
            $("html,body").animate({ scrollTop: 0 }, 420);
        });
        var c = function () {
            var t = $(doc).scrollTop(), h = $(win).height();
            t > 200 ? b.show() : b.hide();
            if (!win.XMLHttpRequest) b.css({ 'top': t + h - 100 });
        };
        $(win).bind("scroll", c);
    };

    var rightSideNav = function (objArr) {
        var ul = $(".nav_list"), lis = ul.find("li"), t = ul.offset().top, objArr = getTop();
        for (var n = 0; n < objArr.length; n++) {
            if (objArr[n].top == undefined) {
                lis[n].style.color = "#d7d7d7";
            };
        };
        function getTop() {
            for (var i in objArr) {
                if (objArr[i].length) {
                    objArr[i].top = objArr[i].offset().top;
                }
            };
            return objArr;
        };
        lis.bind("click", function () {
            var _this = this, top = objArr[$(this).index()].top;
            if (top == undefined) {
                return false;
            };
            $("body,html").animate({ scrollTop: objArr[$(this).index()].top });
            setTimeout(function () { $(_this).addClass("active").siblings().removeClass("active"); }, 500);
        });
        $(window).bind("scroll", function () {
            if ($(this).scrollTop() >= t) {
                ul.addClass("fix");
            }
            else {
                ul.removeClass("fix");
            }
            for (var i = 0, len = objArr.length; i < len; i++) {
                if (objArr[i].top !== undefined && $(this).scrollTop() >= objArr[i].top) {
                    lis.eq(i).addClass("active").siblings().removeClass("active");
                    continue;
                };
            };
            if (objArr[len - 1].top - $(this).scrollTop() < 10) {
                lis.eq(len - 1).addClass("active").siblings().removeClass("active");
            }
        });
    };

    //弹层
    function flipWindow(opt) { return new flipWindow.prototype.init(opt); };
    flipWindow.prototype = {
        init: function (opt) {
            var _this = this;
            this.obj = opt.obj;
            this.onOpen = opt.onOpen;
            this.onClose = opt.onClose;
            this.closeBtnName = "," + opt.closeBtnName || "";
            this.title = opt.title ? "<strong class=\"def_title\">" + opt.title + "</strong>" : "";
            this.tpl = opt.tpl || "";
            this.cssHmltSw = false;
            this.openSw = opt.openSw ? "true" : "false";
            this.obj.bind("click", function () {
                _this.open($(this));
            });
        },
        createCssHtml: function () {
            this.id = new Date().getMilliseconds();
            var css = $("#flipWindowCss").length == 0 ? "<style id=\"flipWindowCss\">.mod_wrap_out{position:absolute;left:50%;top:0;z-index:10;background:#fff}.mod_wrap_out .def_close_btn{position:absolute;right:0;top:0;display:block;width:32px;height:32px;overflow:hidden;background:#01a469}.mod_wrap_out .close_ico2{display:block;margin:10px auto 0;width:13px;height:12px;background:url(/misc/images/front/sprite.png) -224px -145px no-repeat;overflow:hidden}.mod_wrap_out .mod_wrap_in{padding:32px}.mod_wrap_out .def_title{display:block;padding-bottom:10px;text-align:center;color:#01a469;font-size:14px;border-bottom:1px solid #d8d8d8}.mask_bg{position:absolute;background:#000;opacity:.7;filter:alpha(opacity=70);left:0;top:0;width:100%;height:100%;z-index:9}</style>" : "",
                flipHtml = "<div class=\"mod_wrap_out\" id=\"mod_wrap_out" + this.id + "\">"
                    + "        <a href=\"javascript:;\" class=\"def_close_btn\" id=\"def_close_btn" + this.id + "\" title=\"关闭\" ><i class=\"close_ico2\"></i></a>"
                    + "        <div class=\"mod_wrap_in\">"
                    + "            " + this.title
                    + "            <div class=\"wrap_body\">" + this.tpl + "</div>"
                    + "        </div>"
                    + "    </div>",
                maskHtml = $(".mask_bg").length == 0 ? "<div class=\"mask_bg\" style=\"height:" + $("body").height() + "px\"></div>" : this.mask;
            $("body").append(css + flipHtml + maskHtml);
            this.cssHmltSw = true;
        },
        getObj: function () {
            this.mask = $(".mask_bg");
            this.wrapOut = $("#mod_wrap_out" + this.id + "");
            this.closeBtn = $("#def_close_btn" + this.id + "" + this.closeBtnName);
        },
        pos: function () {
            this.wrapOut.css({ "top": (($(window).height() - this.wrapOut.height()) / 2 + $(window).scrollTop()) + "px", "margin-left": -(this.wrapOut.width() / 2) + "px" });
        },
        open: function (obj) {
            if (!this.cssHmltSw) {
                this.createCssHtml();
                this.getObj();
                this.mask.css({ "opacity": .7, "display": "block" });
                this.defClose();
            }
            else {
                this.wrapOut.show();
                this.mask.show();
            }
            if (this.onOpen) {
                this.openSw == "true" ? this.onOpen(obj) : this.openSw == "false" ? (this.onOpen(obj), this.openSw = null) : false;
            }
            this.pos();
        },
        close: function () {
            this.wrapOut.hide();
            this.mask.fadeOut();
            if (this.onClose) {
                this.onClose();
            }
        },
        defClose: function () {
            var _this = this;
            this.closeBtn.bind("click", function () {
                _this.close();
            });
        }
    };
    flipWindow.prototype.init.prototype = flipWindow.prototype;
    return {
        Switchs: Switchs,
        Drag: Drag,
        ScrollBar: ScrollBar,
        rTop: rTop,
        rightSideNav: rightSideNav,
        flipWindow: flipWindow
    };
})(window, document);