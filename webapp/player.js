function Player() {

        this.label = "player";
        this.videoType = 'video/mp4; codecs="avc1.42E01E, mp4a.40.2';
        this.videoElement = null;
        this.stream = null;
        this.mediaSource = new MediaSource();
        this.sourceBuffer = null;
        var self = this;



        self.initPlayer = function() {
            self.videoElement.src = window.URL.createObjectURL(self.mediaSource);
            self.mediaSource.addEventListener('sourceopen', self.onSourceOpen);
        };


        self.onSourceOpen = function () {
            self.sourceBuffer = this.addSourceBuffer(self.videoType);
            console.log("sourceOpen");
        };

        self.onBufferUpdateEnd= function () {
            console.log("Update End");
        };

        self.getData = function(){
            console.log("Geting Data");

            var xhr = new XMLHttpRequest;
            xhr.open('get', self.stream);
            xhr.responseType = 'arraybuffer';
            xhr.onload = function () {
                console.log(xhr.response);
                cb(xhr.response);
            };
            xhr.send();
        };





//
//    this.getData = function (stream, dataRecieverFunc)
//    {
//        console.log("getData");
//
//    };
//
//
//
//    this.onSourceOpen = function(sourceOpenEvent, buff){
//
//        this.getData(this.stream, function (buf) {
//
//            this.sourceBuffer = sourceOpenEvent.target.addSourceBuffer(this.videoType);
//            this.sourceBuffer.addEventListener('updateend', function (_)
//            {
//                console.log(mediaSource.readyState); // ende
//            });
//
//        })
//    };

        return this;
    }
var player = new Player();
player.videoElement = document.querySelector('video');
player.stream = "http://localhost:8081/video/oceans1.mp4";
player.initPlayer();
player.getData();
