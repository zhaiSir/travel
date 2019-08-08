$(document).ready(function(){
    var a_index = 0;
    $("body").click(function(e){
        var a = new Array("❤老婆早上好❤",
            "❤今天又是美美的一天❤",
            "❤吃早饭了吗❤",
            "❤我给你唱首歌把❤",
            "❤好想睡觉❤");
        var $i = $("<span/>").text(a[a_index]);
        a_index = (a_index + 1) % a.length;
        var x = e.pageX,y = e.pageY;
        $i.css({
            "z-index": 99999,
            "top": y - 20,
            "left": x,
            "position": "absolute",
            "font-weight": "bold",
            "color": "#ff6651"
        });
        $("body").append($i);
        $i.animate({"top": y-180,"opacity": 0},1500,function() {
            $i.remove();
        });
    });
});