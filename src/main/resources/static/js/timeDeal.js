const countDownTimer = function (id, date) {
    var _vDate = new Date(date); // 전달 받은 일자
    var _second = 1000;
    var _minute = _second * 60;
    var _hour = _minute * 60;
    var _day = _hour * 24;
    var timer;

    function showRemaining() {
        var now = new Date();
        var distDt = _vDate - now;

        if (distDt < 0) {
            clearInterval(timer);
            document.getElementById(id).textContent = '해당 이벤트가 종료 되었습니다!';
            $.ajax({
                type : "POST",
                url : "/rest/updateTimeDeal",
                data : {pCatecode : '600000'},
                success : function(result) {
                    console.log("할인종료")
                }

            })
            return;
        }

        var days = Math.floor(distDt / _day);
        var hours = Math.floor((distDt % _day) / _hour);
        var minutes = Math.floor((distDt % _hour) / _minute);
        var seconds = Math.floor((distDt % _minute) / _second);

        //document.getElementById(id).textContent = date.toLocaleString() + "까지 : ";
        document.getElementById(id).textContent =  ' ';
        document.getElementById(id).textContent += hours + ': ';
        document.getElementById(id).textContent += minutes + ': ';
        document.getElementById(id).textContent += seconds + '';
    }

    timer = setInterval(showRemaining, 1000);
}

var dateObj = new Date();
dateObj.setDate(dateObj.getDate() + 1);

countDownTimer('sample01', '06/20/2022 11:10 AM');
// countDownTimer('sample02', '04/01/2024 00:00 AM'); // 2024년 4월 1일까지, 시간을 표시하려면 01:00 AM과 같은 형식을 사용한다.
// countDownTimer('sample03', '04/01/2024'); // 2024년 4월 1일까지
// countDownTimer('sample04', '04/01/2019'); // 2024년 4월 1일까지