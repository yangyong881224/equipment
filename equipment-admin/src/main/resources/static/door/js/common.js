var com = (function (win, doc) {
    var com = {};
    com.winW = $(win).width();
    com.winH = $(win).height();
    //判断浏览器类型及版本, 使用方法：com.ifBrowser("chrome"),返回的是指定浏览器的版本号, 如果不是指定的浏览器，则返回undefined;
    com.ifBrowser = function (str) {
        var Sys = [], ua = navigator.userAgent.toLowerCase(), s;
        (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
        (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
        (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
        (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
        (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
        return Sys[str];
    };
    //冒泡阻止
    com.prevent = function (e) {
        e && e.stopPropagation ? e.stopPropagation() : win.event.cancelBubble = true;
    };
    //阻止浏览器的默认行为 
    com.stopDefault = function (e) {
        e && e.preventDefault ? e.preventDefault() : win.event.returnValue = false;
        return false;
    };
    //去掉表单元素默认的值obj参数为JQ对象
    com.focuBlur = function (obj) {
        for (var i = 0; i < obj.length; i++) {
            obj.eq(i).bind("focus", function () {
                this.style.color = "#666";
                this.value = this.value == this.defaultValue ? "" : this.value;
            });
            obj.eq(i).bind("blur", function () {
                this.style.color = "#999";
                this.value = this.value == "" ? this.defaultValue : this.value;
            });
        };
        return com.focuBlur;
    };

    //获取URL参数
    com.getRequest = function () {
        var url = location.search;
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
            }
        }
        return theRequest;
    };

    //创建JS
    com.loadSript = function (url, callback) {
        var script = doc.createElement("script");
        script.type = "text/javascript";
        script.src = url;
        script.async = 'async';
        doc.getElementsByTagName("head")[0].appendChild(script);
        if (script.readyState) {
            script.onreadystatechange = function () {
                if (script.readyState == "loaded" || script.readyState == "complete") {
                    script.onreadystatechange = null;
                    if (callback) { callback(); }
                }
            };
        }
        else {
            script.onload = function () {
                if (callback) { callback(); }
            }
        }
    };

    //cookie
    com.setCookie = function(c_name, value, exdays) {//设置cookie
        var exdate = new Date();
        exdate.setDate(exdate.getDate() + exdays);
        var c_value = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toUTCString());
        doc.cookie = c_name + "=" + c_value + ';path=/;domain=.to8to.com';
    }
    com.getCookie = function (c_name) {//获得cookie值
        if (doc.cookie.length > 0) {
            c_start = doc.cookie.indexOf(c_name + "=")
            if (c_start != -1) {
                c_start = c_start + c_name.length + 1
                c_end = doc.cookie.indexOf(";", c_start)
                if (c_end == -1) c_end = doc.cookie.length
                return unescape(doc.cookie.substring(c_start, c_end))
            }
        }
        return ""
    };

    // function switchTab(triggerObject, showLayer, event, className, callback) {

    //     this.trigger = {};
    //     this.layer = {};
    //     this.event = 'click';
    //     this.allowEvent = ['click', 'mouseover'];
    //     this.selectItem = 0;
    //     this.selectClass = '';

    //     /**
    //      * 用于初始化对象和显示层
    //      */
    //     this.init = function() {
    //         this.event = event ? event : this.event;
    //         this.selectClass = className ? 　className : this.selectClass;
    //         if (!in_array(this.event, this.allowEvent)) {
    //             throw new Error('not allow event type');
    //         }
    //         var triggerType = typeof triggerObject,
    //             layerType = typeof showLayer;
    //         if (toString.apply(triggerObject) === '[object Array]' || toString.apply(showLayer) === '[object Array]') {
    //             //如果参数triggerObject是一个数组，那么默认传过来的是DOM对象的id  
    //             if (toString.apply(triggerObject) === '[object Array]') {
    //                 this.trigger = $(_join(triggerObject));
    //             } else {
    //                 this.layer = $(_join(showLayer));
    //             }
    //         }
    //         if (triggerType === 'string' || layerType === 'string') {
    //             //对象集合的class属性
    //             triggerType === 'string' ? this.trigger = $('.' + triggerObject) : this.layer = $('.' + showLayer);
    //         }
    //         if (toString.apply(triggerObject) === '[object Object]' || toString.apply(showLayer) === '[object Object]') {
    //             //对象集合
    //             triggerType === 'object' ? this.trigger = triggerObject : this.layer = showLayer;
    //         }

    //         this.bind();
    //         if (typeof callback === 'function') {
    //             callback();
    //         }
    //     };

    //     //初始化绑定事件
    //     this.bind = function() {
    //         var _this = this;
    //         this.trigger.bind(this.event, function() {
    //             _this.selectItem = _this.trigger.index($(this));
    //             if (_this.selectClass) {
    //                 _this.trigger.removeClass(_this.selectClass);
    //                 $(this).addClass(_this.selectClass);
    //             }
    //             _this.layer.hide();
    //             _this.layer.eq(_this.selectItem).show();
    //         });
    //     };

    //     this.init();
    //     console.log(this.trigger, this.layer);
    // }

    // function in_array(value, arr) {
    //     if (!arr || arr.length == 0) return false;
    //     var flag = false;
    //     for (var i = 0, j = arr.length; i < j; i++) {
    //         if (arr[i] == value) {
    //             flag = true
    //         }
    //     };
    //     return flag
    // };

    // $(document).ready(function() {
    //     new switchTab($('.cate_list li'), $('#cate_main li'), 'mouseover', 'active');    
    // })
    
    //全部分类切换
    var indexCate = (function () {
        var main = $("#cate_box"), list = main.find(".cate_list"), listLis = list.find("li"), conMain = $("#cate_main"), conLis = conMain.find("li"), Index = 0;
        var ind = getCookie('ind',1);
        listLis.hover(function () {
            Index = $(this).index();
            $(this).addClass("active").siblings().removeClass("active");
            conMain.show();
            conLis.eq(Index).show().siblings().hide();
            //图片处理
            conLis.eq(Index).find("img").each(function () {
                $(this).attr("src", $(this).attr("data-src"));
            });
        });
        main.bind("mouseleave", function () {
            listLis.eq(Index).removeClass("active");
            conMain.hide();
            conLis.eq(Index).hide();
        });

        //type2
        if ($(".mod_cate_type2").length) {
            var t2 = $(".mod_cate_type2").find(".cate_title"), larr = [main, $(".mod_cate_type2")];
            for (var i = 0; i < larr.length; i++) {
                larr[i].bind("mouseleave", function () {
                    main.hide();
                    t2.removeClass("active");
                });
            };
            t2.hover(function () {
                main.show();
                t2.addClass("active");
            });
        }

    })();

    //首页用户菜单，关注土巴兔 
    com.topBar = function () {
        if (com.ifBrowser("ie") == "6.0") {
            $(".top_nav").find(".show_some").hover(function () {
                $(this).find(".show_box").addClass("show");
            }, function () {
                $(this).find(".show_box").removeClass("show");
            });
        };
    };

    //有消息来的时候
    com.topBarMsg = function (text, url) {
        var msg = $(".msg_box");
        msg.find(".msg_a").text(text).attr("href", url);
        msg.animate({ "height": "22px" }, 700, function () {
            $(this).addClass("msg_box_show");
        });
        msg.find(".close_a").bind("click", function () {
            msg.animate({ "height": "0" }, 700);
        });
    };

    //尾部
    com.footer = function () {
        var lis = $("#footer").find(".foot_tag_list li"), timer = null, i = 0, len = lis.length, t = $("#footer").find(".foot_tag").offset().top, b = true;
        $(win).bind("scroll", function () {
            if (b && (($(this).scrollTop() + com.winH) > t)) {
                shows();
            }
        });
        function shows() {
            timer = setInterval(function () {
                lis.eq(i).animate({ "bottom": 0 });
                i++;
                if (i == len) {
                    clearInterval(timer);
                }
            }, 50);
            b = false;
        };
        if (t < com.winH) {
            shows();
        }
    };
    //右侧菜单
    com.sideMenu = (function (tpl) {
        var ind = getCookie('ind',1);
        var html = "<div class=\"side_menu\">"
            if(ind != "sj")
            html+= "        <a href=\"#\" title=\"回到顶部\"><i class=\"t_arrow_g2\"></i><em>回到顶部</em></a>"
                + "    </div>";
        var b = $(tpl || html).appendTo($("body"));
        if (!win.XMLHttpRequest) {
            var c = function () {
                var t = $(doc).scrollTop(), h = $(win).height();
                b.css({ 'top': t + h - 300 });
            };
            $(win).bind("scroll", c);
        }
    })();
    
    //头部搜索
    var headSearch = (function () {
        var form = $("form[name=search]"),
            txt = form.bind("submit", function () {
                if (form.find(".mod_text")[0].value == "搜下有什么好东西") return false;
            }).find(".mod_text");
        txt.bind("focus", function () {
            if (txt[0].value == "搜下有什么好东西") txt[0].value = "";           
            txt[0].style.color = "#666";
            if($.trim(txt[0].value))
                $(txt[0]).parent().parent().append('<ul class="search_shop"><li><a href="/shop/?q='+ txt[0].value +'" class="search_words"><img src="/misc/images/front/search_shop.png" width="19" height="19" /><span>搜索<em>“'+ txt[0].value +'”</em>相关店铺或卖场</span></a></li></ul>');
        });
        txt.bind("keyup", function () {
            var _val = $.trim(txt[0].value);
            if($(txt[0]).parent().parent().find('ul.search_shop').size() > 0)
            {
                $(txt[0]).parent().parent().find('ul.search_shop').find('em').html('“'+_val+'”');
                $(txt[0]).parent().parent().find('ul.search_shop').find('a.search_words').attr('href','/shop/?q='+ txt[0].value);
            }
            else
                $(txt[0]).parent().parent().append('<ul class="search_shop"><li><a href="/shop/?q='+ txt[0].value +'" class="search_words"><img src="/misc/images/front/search_shop.png" width="19" height="19" /><span>搜索<em>“'+ _val +'”</em>相关店铺或卖场</span></a></li></ul>');
        });

        txt.bind("blur", function () {
            if (txt[0].value == "") txt[0].value = "搜下有什么好东西";            
            txt[0].style.color = "#999";
        });
    })();
    $('body').live('click',function(event){
        var e = window.event || event;
        var elem = e.srcElement || e.target;
        if((elem.tagName == 'A' && elem.className == 'search_words') || (elem.tagName == 'INPUT' && elem.className == 'mod_text'))
        {
            if(e.stopPropagation)
                e.stopPropagation();
            else
                e.cancelBubble = true;
            return false;
        }
        $('div.mod_search').find('ul.search_shop').remove();
    });
    
    //头部用户菜单，关注土巴兔 只针对IE6 
    com.topBar();
    //尾部效果
    com.footer();
    //加载弹框样式
    return com;
})(window, document);
$("img").lazyload({ placeholder: "/images/grey.gif", failurelimit: 30, threshold : 500, effect: "fadeIn", skip_invisible: false });

function getCookie(name,pre)
{
    if(pre)
        name='to8to_'+name;
    var r=new RegExp("(\\b)"+name+"=([^;]*)(;|$)");
    var m=document.cookie.match(r);
    return(!m?"":decodeURIComponent(m[2]));
}

//设置uid 身份
username = getCookie('username',1);

if(typeof(username)!='undefined'&&username!=""&&username!="deleted"){
    $(".login_link").remove();
    $(".reg_link").remove();
    $(".dash_line").eq(0).remove();
    $("#nav_user_data").html('');


    //用户身份
    var ind = getCookie('ind',1);
    //用户id
    var uid = getCookie('uid',1);


    var role_sj = '<li class="user_li show_some">'
        + '<a href="/my/"><p>'
        + username + '</p></a>'
        //+ '<s class="arrow_s small_arrow"></s>'
        + '<dl class="show_box">'
        + '<dt class="arr_div"><s class="arrow_s"></s></dt>'
        + '<dd><i class="home_ico"></i><a href="/my/">商家后台</a></dd>'
        + '<dd><i class="ppgl_ico"></i><a href="/my/brands/">品牌管理</a></dd>'
        + '<dd><i class="xzdp_ico"></i><a href="/my/shops/edit/">新增店铺</a></dd>'
        + '<dd><i class="dpgl_ico"></i><a href="/my/shops/offer/">店铺管理</a></dd>'
        + '<dd><i class="gghh_ico"></i><a href="/my/shops/exchange_ad/">免费广告互换</a></dd>'
        + '<dd><i class="qyrz_ico"></i><a href="/my/shops/auth/">企业认证</a></dd>'
        /*+ '<dd><i class="xx_ico"></i><a href="#">我的消息<b class="msg_num msg_ico"><em>15</em></b></a></dd>'*/
        + '<dd class="out_login"><i class="out_login_ico"></i><a href="http://www.to8to.com/logout.php?uid=' + uid + '">退出</a></dd>'
        + '</dl></li>';

    var role_other = '<li class="user_li show_some">'
        + '<a href="/my/reviews/"><p>'
        + username + '</p></a>'
        //+ '<s class="arrow_s small_arrow"></s>'
        + '<dl class="show_box">'
        + '<dt class="arr_div"><s class="arrow_s"></s></dt>'
        + '<dd><i class="kb_ico"></i><a href="/my/reviews/">我发布的点评</a></dd>'
        + '<dd><i class="gwc_ico"></i><a href="/favorite/">我的收藏</a></dd>'
        + '<dd><i class="yhj_ico"></i><a href="/my/coupons/">我的优惠券</a></dd>'
        + '<dd><i class="lpj_ico"></i><a href="/my/gifts/">我的礼品券</a></dd>'
        /*+ '<dd><i class="xx_ico"></i><a href="#">我的消息<b class="msg_num msg_ico"><em>15</em></b></a></dd>'*/
        + '<dd class="out_login"><i class="out_login_ico"></i><a href="http://www.to8to.com/logout.php?uid=' + uid + '">退出</a></dd>'
        + '</dl></li>';

   /* if($('#header .top_nav').length > 0){

        $('#header .top_nav').prepend( ind == 'sj' ? role_sj : role_other );
    }*/

    if($('#top_nav').length > 0){

        $('#top_nav').prepend( ind == 'sj' ? role_sj : role_other);
    }
}


var to8to_tname = getCookie('tname',1);
if(typeof(to8to_tname)!='undefined'&&to8to_tname!=""&&to8to_tname!="deleted"){

    $('#current_city').html(to8to_tname);
}

/*
 * 判断 sourcepage 与 landpage cookie 是否存在，不存在则设置cookie
 */
function checkCookie() {
    var value = com.getCookie('to8to_sourcepage');
    if (value != null) {
    } else {
        com.setCookie('to8to_sourcepage', encodeURI(document.referrer), 90);
    }
    var value = com.getCookie('to8to_landpage');
    if (value != null) {
    } else {
        com.setCookie('to8to_landpage', encodeURI(location.href), 90);
    }
    var tvalue = com.getCookie('to8to_landtime');
    if (tvalue != null) {
    } else {
        com.setCookie('to8to_landtime', Date.parse(new Date())/1000, 90);
    }
}

/*
 * 根据当前的地址，判断是否来自推广，如果来自推广，则搜集相关内容，然后发送ajax请求
 */
function seoKeywords()
{
    var word={};
    var cur_url = decodeURI(location.href);
    var engine_pos = cur_url.indexOf('utm_from=');
    var keyword_pos = cur_url.indexOf('utm_keyword=');
    var keywords = '';
    var cur_ref = decodeURI(document.referrer);
    if( engine_pos != -1 && keyword_pos != -1 )
    {
        sstart = cur_url.indexOf('=', engine_pos)+1;
        send = cur_url.indexOf('&', engine_pos);
        search_engine = cur_url.substring(sstart, send);
        word['engine'] = search_engine;
        switch(search_engine)
        {
            case 'baidu': //根据referrer获取搜索关键词
                wd_pos = cur_ref.indexOf('wd=');
                sstart = cur_ref.indexOf('=', wd_pos) + 1;
                send = cur_ref.indexOf('&', wd_pos);
                keywords = cur_ref.substring(sstart, send);
                word['keywords'] = keywords;
                break;
            case 'google':
                break;
            case '360':
                break;
            case 'sogou':
                alert("sogou");
                wd_pos = cur_ref.indexOf('wd=');
                sstart = cur_ref.indexOf('=', wd_pos) + 1;
                send = cur_ref.indexOf('&', wd_pos);
                keywords = cur_ref.substring(sstart, send);
                word['keywords'] = keywords;
                break;
        }

        sstart = cur_url.indexOf('=', keyword_pos) + 1;
        send = cur_url.indexOf('&', keyword_pos);
        word['keywordid'] = cur_url.substring(sstart, send);

    }
    return word;
}

checkCookie();
var keywords = seoKeywords();
if(keywords.keywordid && keywords.keywordid.length > 0)
{
    $.get('http://www.to8to.com/api/seo_keywords_process.php', keywords);
}
var encodeUrl = encodeURIComponent(document.location);
if(encodeUrl.search('reg')==-1 && encodeUrl.search('logout')==-1 && encodeUrl.search('login')==-1){
    com.setCookie('to8to_nowpage',encodeUrl,3600*1000,1);
}
$(function(){
    var to8to_auth = com.getCookie('to8to_auth');
    var to8to_uid  = com.getCookie('to8to_uid');
    var to8to_haslogin = com.getCookie('to8to_haslogin');
    if(to8to_uid&&to8to_auth&& to8to_uid!=to8to_haslogin){
        $.post('http://www.to8to.com/api/insert_user_login.php',{to8to_auth:to8to_auth,to8to_uid:to8to_uid},function(data){
            com.setCookie('to8to_haslogin',to8to_uid);
        },'json');
    }
    if($(".mod_search").find("form").length>0)
    {
        $(".mod_search").find("form").submit(function(){
            $v = $.trim($(".mod_search").find("form").find("input[class=mod_text]").val());
            if($v=="")
            {
                return false;
            }
        })
    }
});

//头部底部初始化
!(function(){
    var a  = {
        init:function(){
            hfDocReady();//header and footer docReadyFunction
        }
    };
    var searchclicktage = '', searchtage = '', searchput='keyword', sHref='',sText='';
        var tcode = getCookie("tcode",true);
            tcode = tcode?tcode:'sz';
    function initsearch(sName)
        {
           sName = $.trim(sName);
           switch(sName)
           {
                case '效果图': 
                   searchtage='1_8_3_1';
                   searchclicktage='1_8_2_1'; 
                   sHref= 'http://xiaoguotu.to8to.com/search.php';
                   sText ='海量精美效果图任你选'; 
                break;   
                case '装修公司':
                   searchtage='1_8_3_2';
                   searchclicktage='1_8_2_2';
                   sHref= "http://"+tcode+".to8to.com/company/";
                   sText ='挑选您心仪的装修公司';
                break;   
                case '小区':
                    searchtage='1_8_3_3';
                    searchclicktage='1_8_2_3';
                    sHref=  "http://"+tcode+".to8to.com/zwj/";
                    sText ='找找您家小区的装修案例';break;   
                case '文章': searchtage='1_8_3_4';searchclicktage='1_8_2_4';    sHref= 'http://www.to8to.com/yezhu/xzx_search.php';
                    sText ='了解装修相关的资讯知识';
                    break;   
                case '问答': 
                    searchtage='1_8_3_5';
                    searchclicktage='1_8_2_5';    
                    sHref= 'http://www.to8to.com/ask/search.php';
                    sText ='解决您的装修疑问';
                    break;   
                case '建材': 
                    searchtage='1_8_3_6';
                    searchclicktage='1_8_2_6';
                    sHref= '/search_product.html';
                    sText ='挑选优质家居建材'; 
                    break;    
           }
           $('.header_search_submit').attr('searchtage',searchtage);
           if(sName=='全站'||sName=='文章' || sName=='小区')
           {
                searchput ='keyword_zh';
           }
           else if(sName=='建材')
           {
                searchput ='q';
           }
           else
           {
              searchput='keyword';
           }
            $('.header_search_input').attr('name',searchput);
            $('#searchform').attr('action',sHref);
            $('.header_search_input_text').html(sText);
        }
    function hfDocReady(){
        
        var doc = {};
        doc.hs = $('.header_select');
        doc.si =  $('.header_search_input');
        doc.si.val("");
        doc.hs.on('mouseenter', function(){
          $(this).addClass('on');
          $(this).find('ul').show();
        });
        doc.hs.on('mouseleave', function(){
          $(this).removeClass('on');
          $(this).find('ul').hide();
        });
        var currentTxt = doc.hs .find('a >span>em').text();
        initsearch(currentTxt);
        doc.hs.find('ul > li').on("click", function(){
          var sName = $(this).find('a').text(),
                  siWidth = doc.si.width();
                  hsWidth = $('.header_select').width();
           $('.header_select_sort').find('span > em').text(sName);
           initsearch(sName);
           try{clickStream.getCvParams(searchclicktage);}catch(e){}
           newHsWidth = $('.header_select').width();
           doc.si.width(siWidth - (newHsWidth-hsWidth));
           var rm = $('.header_search_input_text').attr('v');
           if(rm == undefined) rm = 83;
           var rightMargin = rm-(newHsWidth-hsWidth);
           $('.header_search_input_text').css('right', ''+rightMargin+'px').attr('v',''+rightMargin+'');
           $(this).parent().hide();
           doc.si.focus();
        });
        $('.header_search_input_text').on("click", function(){
          doc.si.focus();
        });
        doc.si.on("keydown", function(){
          $('.header_search_input_text').hide();
        });
        doc.si.blur(function(){
          if($(this).val() =="" )  $('.header_search_input_text').show();
        });
        $('.header_menu >ul > li').on("mouseenter", function(){
          $(this).addClass('menu_hover');
        });
        $('.header_menu >ul > li').on("mouseleave", function(){
          $(this).removeClass('menu_hover');
        });
        $('.header_search_submit').on('click',function(){
            var searchtage=$('.header_search_submit').attr('searchtage');
            if(searchtage>0)
            {
                try{clickStream.getCvParams(searchtage);}catch(e){} 
            }
        });
        };
        window.headerFooter = a;
		
		//页脚友链等tab切换
		 $(".mod_title_tab").find("span").hover(function() {
            if($(this).hasClass('bottom_tab_active')) return false;
            $('.bottom_tab_active').removeClass('bottom_tab_active');
            $(this).addClass("bottom_tab_active");
            $('div.bottom_data_active').removeClass('bottom_data_active');
            $('div.mod_switch_b').find('div.bottom_data').eq($(this).index()).addClass('bottom_data_active');
        });
    
})(jQuery);

//提交前验证
function checkSerachForm(){
	input = $('.header_search_input');
	val = input.val();
	maxwords = 40; //20个汉字
	len = val.replace(/[^\x00-\xff]/g,"aa").length;
    if(val =="" || len > maxwords){
		if(len > maxwords){
			alert('关键字控制在'+Math.ceil(maxwords/2)+'个汉字字符以内,搜索结果更精准!');
			return false;
		}
       input.focus();
       return false
    }else{
       return true;
    }
}