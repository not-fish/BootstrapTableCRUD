
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


}


