
autumn();

//鼠标触碰模糊变化
function autumn() {
    var images = document.querySelectorAll('.autumnImgClass');

    document.querySelector('.autumnHeaderClass').addEventListener('mousemove',(e) =>{
        let percentage = e.clientX / window.outerWidth;
        let offset = 10*percentage;
        let blur = 20;

        for(let [index,image] of images.entries()){
            offset *=1.3;

            let blurValue = (Math.pow((index / images.length - percentage),2) * blur);

            image.style.setProperty('--offset',`${offset}px`);
            image.style.setProperty('--blur',`${blurValue}px`);
        }
    });

//定时眨眼
    setInterval("girlAnimation()", 4000);
}

function girlAnimation() {

    setTimeout(function () {
        $('#girlPic').attr("src","/img/autumn/a2_2.png");
    },500);
    setTimeout(function () {
        $('#girlPic').attr("src","/img/autumn/a2_3.png");
    },600);
    setTimeout(function () {
        $('#girlPic').attr("src","/img/autumn/a2_2.png");
    },700);
    setTimeout(function () {
        $('#girlPic').attr("src","/img/autumn/a2_1.png");
    },800);
}
