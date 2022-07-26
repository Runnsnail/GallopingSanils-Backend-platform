function goLogin(){
    var postdata = {
        loginEmail:$("#loginEmail").val(),
        loginPassword:$("#loginPassword").val()

    }
    $.ajax({
        type:"POST",
        //type:"GET",
        url:"/login/userlogin",
        data:postdata,
        //返回数据的格式
        datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
        success:function(data){
            if (data.code == 200) {
                //
                localStorage.setItem('Authorization', data.token)
                window.location.href="/html-customizer/ltr/vertical-menu-template-dark/dashboard-ecommerce.html";
            }
        }
    })};
