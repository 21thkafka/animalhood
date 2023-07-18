$(document).ready(function(){
    console.log("common-paging 로딩");
    var html = "";
    var totalPage = 3;
    var endPage = document.getElementById('endPage').value;
    var url = window.location.href;
    var pageNo = url.charAt(url.length - 1);;
    for(i = 1; i <= endPage; i++){
        if(i == pageNo){

            html += "<a href=\"#\" title='현재페이지' class\"on\">"+pageNo+"</a>"
        } else if (i != pageNo){
            html += "<a href=\""+i+"\" >"+i+"</a>"
        }
    }
    $("#pagingDiv").html(html);
});
