(function ($) {
    "use strict";

    /*********************************
    /* Table of Context
    �߶�ģ�壺http://www.bootstrapmb.com
    /* *******************************
    /* 
    /* Preloader
    /* Sticky Navbar
    /* Scroll Top Bar
    /* Mobile Menu Flyout Menu
    /* Mobile Menu Expand
    /* Nice Select
    /* Movie Slider
    /* Thriller Slider
    /*  Gallery Slider
    /* Add/Minus Quantity
    /* CountDown 
    /*  Swiper Hero Slider 
    /* Testimonial Slider
    /* Map

    *********************************/

    /*********************************
    /* Preloader Start
    *********************************/
    $(window).on("load", function () {
        $("#status").fadeOut();
        $("#preloader").delay(500).fadeOut("slow");
        $("body").delay(500).css({ overflow: "visible" });
    });

    /*********************************
    /* Sticky Navbar
    *********************************/
    $(window).scroll(function () {
        var scrolling = $(this).scrollTop();
        var stikey = $(".header-sticky");

        if (scrolling >= 50) {
            $(stikey).addClass("nav-bg");
        } else {
            $(stikey).removeClass("nav-bg");
        }
    });

    /*********************************
    /*  Mobile Menu Flyout Menu
    *********************************/
    $(".toggler__btn").on("click", function (event) {
        event.preventDefault();
        $(".flyoutMenu").toggleClass("active");
    });
    $(".closest__btn").on("click", function (event) {
        event.preventDefault();
        $(".flyoutMenu").toggleClass("active");
    });

    $(document).on("click", function (e) {
        if ($(e.target).closest(".flyout__inner").length === 0 && $(e.target).closest(".toggler__btn").length === 0) {
            $(".flyoutMenu").removeClass("active");
        }
    });

    /*********************************
    /*  Mobile Menu Expand
    *********************************/
    $(".flyout-main__menu .nav__link").on("click", function (event) {
        event.preventDefault();
        // $(".has__dropdown").find(".sub__menu").slideUp();
        $(this).parent(".has__dropdown").find(".sub__menu").slideToggle();
    });

    $(".flyout-main__menu .sub__menu .nav__link").on("click", function (event) {
        event.preventDefault();
        $(this).parent(".has__dropdown").find(".sub__sub-menu").slideToggle();
    });

    /*********************************
    /* Add/Minus Quantity
    *********************************/
    $(".incressQnt").on("click", function () {
        var $qty = $(this).closest("div").find(".qnttinput");
        var currentVal = parseInt($qty.val());
        if (!isNaN(currentVal)) {
            $qty.val(currentVal + 1);
        }
    });
    $(".decressQnt").on("click", function () {
        var $qty = $(this).closest("div").find(".qnttinput");
        var currentVal = parseInt($qty.val());
        if (!isNaN(currentVal) && currentVal > 1) {
            $qty.val(currentVal - 1);
        } else {
            $(this).parents(".cart__action__btn").find(".cart__quantity").css("display", "none");
        }
    });


})(jQuery);
