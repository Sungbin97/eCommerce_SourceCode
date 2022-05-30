(function (){

    $.getJSON("/rest/getCategoryList/",function (data){
        let str ="";
        let shopCategory_ul1=$(".firstCategory")
        $(data).each(function (index,tier2){
            if(tier2.cateParent=='100000'){
                str +='<div class="shopCategory_div">'
                str += '<li><a class="tier2_a" style="font-size: 15px" ' +
                    'href="/shop/list?pCateCode='+tier2.pcateCode+'&tier='+tier2.tier+'&page=1&numPerPage=10&sort=\'\'&keyword=\'\'"><strong>'+tier2.pcateName+'</strong></a>'
                str += '<ul>';
                $(data).each(function (index2,tier3){
                    if(tier3.cateParent==tier2.pcateCode){
                        str +='<li><a class="tier3_a" style="font-size: 13px" ' +
                            'href="/shop/list?pCateCode='+tier3.pcateCode+'&tier='+tier3.tier+'&page=1&numPerPage=10&sort=\'\'&keyword=\'\'">'+tier3.pcateName +'</a></li>'
                    }
                })
                str += '</ul>'
                str +='</li>';
                str +='</div>'
            }

        })
        shopCategory_ul1.html(str)
    })
    $.getJSON("/rest/getCategoryList/",function (data){
        let str ="";
        let shopCategory_ul2=$(".secondCategory")
        $(data).each(function (index,tier2){
            if(tier2.cateParent=='200000'){
                str +='<div class="shopCategory_div">'
                str += '<li><a class="tier2_a" style="font-size: 15px" ' +
                    'href="/shop/list?pCateCode='+tier2.pcateCode+'&tier='+tier2.tier+'&page=1&numPerPage=10&sort=\'\'&keyword=\'\'"><strong>'+tier2.pcateName+'</strong></a>'
                str += '<ul>';
                $(data).each(function (index2,tier3){
                    if(tier3.cateParent==tier2.pcateCode){
                        str +='<li><a class="tier3_a" style="font-size: 13px" ' +
                            'href="/shop/list?pCateCode='+tier3.pcateCode+'&tier='+tier3.tier+'&page=1&numPerPage=10&sort=\'\'&keyword=\'\'">'+tier3.pcateName +'</a></li>'
                    }
                })
                str += '</ul>'
                str +='</li>';
                str +='</div>'
            }

        })
        shopCategory_ul2.html(str)
    })
    $.getJSON("/rest/getCategoryList/",function (data){
        let str ="";
        let shopCategory_ul3=$(".thirdCategory")
        $(data).each(function (index,tier2){
            if(tier2.cateParent=='300000'){
                str +='<div class="shopCategory_div">'
                str += '<li><a class="tier2_a" style="font-size: 15px" ' +
                    'href="/shop/list?pCateCode='+tier2.pcateCode+'&tier='+tier2.tier+'&page=1&numPerPage=10&sort=\'\'&keyword=\'\'"><strong>'+tier2.pcateName+'</strong></a>'
                str += '<ul>';
                $(data).each(function (index2,tier3){
                    if(tier3.cateParent==tier2.pcateCode){
                        str +='<li><a class="tier3_a" style="font-size: 13px" ' +
                            'href="/shop/list?pCateCode='+tier3.pcateCode+'&tier='+tier3.tier+'&page=1&numPerPage=10&sort=\'\'&keyword=\'\'">'+tier3.pcateName +'</a></li>'
                    }
                })
                str += '</ul>'
                str +='</li>';
                str +='</div>'
            }

        })
        shopCategory_ul3.html(str)
    })
    $.getJSON("/rest/getCategoryList/",function (data){
        let str ="";
        let shopCategory_ul4=$(".fourthCategory")
        $(data).each(function (index,tier2){
            if(tier2.cateParent=='400000'){
                str +='<div class="shopCategory_div">'
                str += '<li><a class="tier2_a" style="font-size: 15px" ' +
                    'href="/shop/list?pCateCode='+tier2.pcateCode+'&tier='+tier2.tier+'&page=1&numPerPage=10&sort=\'\'&keyword=\'\'"><strong>'+tier2.pcateName+'</strong></a>'
                str += '<ul>';
                $(data).each(function (index2,tier3){
                    if(tier3.cateParent==tier2.pcateCode){
                        str +='<li><a class="tier3_a" style="font-size: 13px" ' +
                            'href="/shop/list?pCateCode='+tier3.pcateCode+'&tier='+tier3.tier+'&page=1&numPerPage=10&sort=\'\'&keyword=\'\'">'+tier3.pcateName +'</a></li>'
                    }
                })
                str += '</ul>'
                str +='</li>';
                str +='</div>'
            }

        })
        shopCategory_ul4.html(str)
    })







})(jQuery);
