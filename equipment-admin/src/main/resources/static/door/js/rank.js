(function () {
    //排行傍顶部切换
    (function () {
        var ul = $(".c_list"), lis = ul.find("li"), title = ul.find(".t_s"), morebox = $(".c_more"), mores = morebox.find(".more_s"), arrows = $(".c_arrows").find("span"), liw = lis.eq(0).width(), now = 0, timer = null;
        ul.width(lis.length * (liw + 3));
        for (var i = 0, len = title.length; i <= len; i++) {
            mores.eq(i).css("left", (liw + 3) * i);
            (function (Index) {
                lis.eq(Index).bind("mouseover", function () {
                    lis.eq(Index).addClass("active").siblings().removeClass("active");
                    mores.eq(Index + 1).addClass("show").siblings().removeClass("show");
                });
                mores.bind("mouseleave", function () {
                    mouse_out(lis.eq(Index));
                });
            })(i);
        };
        $(".mod_ranking_choose").bind("mouseleave", function () {
            mouse_out(lis);
            auto();
        });
        $(".mod_ranking_choose").bind("mouseover", function () {
            clearInterval(timer);
        });
        function mouse_out(obj) {
            obj.removeClass("active");
            mores.removeClass("show");
        };
        function goto() {
            ul.animate({ "left": -now * (liw + 3) });
            morebox.css("left", -now * (liw + 3));
            arrows.removeClass("no");
            if (now > (lis.length - 8)) {
                arrows.eq(1).addClass("no");
            }
            if (now <= 0) {
                arrows.eq(0).addClass("no");
            }
            else {

            }
        };
        function auto() {
            if (lis.length <= 6) return;
            clearInterval(timer);
            timer = setInterval(function () {
                now++;
                now %= (lis.length - 6);
                goto();
            }, 5000);
        };
        arrows.eq(1).bind("mousedown", function () {
            if (now > (lis.length - 8)) {
                arrows.eq(1).addClass("no");
                return false;
            }
            now++;
            arrows.removeClass("no");
            goto();
            return false;
        });
        arrows.eq(0).bind("mousedown", function () {
            if (now <= 0) {
                arrows.eq(0).addClass("no");
                return false;
            }
            now--;
            arrows.removeClass("no");
            goto();
            return false;
        });
        setTimeout(function () { auto() }, 200);
        $(window).bind("focus", function () {
            auto();
        });
        $(window).bind("blur", function () {
            clearInterval(timer);
        });
    })();
    //排行榜切换
    (function () {
        var main = $(".r_l_main"), list = $(".ranking_list");
        for (var i = 0; i < main.length; i++) {
            (function (Index) {
                //type3
                if (main.eq(Index).hasClass("r_l_main_t3")) {
                    var lis = main.eq(Index).find(".r_l_list li");
                    lis.hover(function () {
                        var img = $(this).find("img");
                        lis.attr("class", "");
                        $(this).addClass("active");                      
                        if (img.attr("src") == img.attr("data-src")) return;
                        img.attr("src", img.attr("data-src"));
                    }, function () {
                        $(this).removeClass("active");
                    });
                }
                else {
                    $(".r_l_list").find("li").bind("mouseover", function () {
                        var img = $(this).find("img");
                        $(this).addClass("active").siblings().removeClass("active");
                        if (img.attr("src") == img.attr("data-src")) return;
                        img.attr("src", img.attr("data-src"));
                    });
                }
            })(i);
        };
        //点击切换
        $(".top_s").bind("click", function () {
            $(this).addClass("active").siblings().removeClass("active");
            list.addClass("hide").eq($(this).index()).removeClass("hide");
        });
    })();
    //侧栏切换
    (function () {
        var lis = $(".ask_list").find("li"), div = lis.find(".detail_text");
        lis.hover(function () {
            div.hide();
            div.eq($(this).index()).show();
        });
    })();
    //底部更多点评加载
    (function () {
        $('div.more_comment').click(function(){
            var _this = $(this);
            $.post('/ajax/get_top_com/',{page:page,cid:cid},function(msg){
                var total = msg.total;
                var data = msg.data;
                if(data){
                    _this.prev('ul.shop_list').append(data);
                }else
                    _this.hide();
                if(!total)
                    _this.hide();
                else
                    _this.html('查看更多'+total+'位用户的点评<i class="b_arrow_g2"></i>');
            },'json');
            page = parseInt(page) + 1;
        });
    })();
})();