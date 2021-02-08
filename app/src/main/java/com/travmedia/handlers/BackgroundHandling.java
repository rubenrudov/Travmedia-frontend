package com.travmedia.handlers;
import com.travmedia.R;

import java.util.Random;
public class BackgroundHandling {
    /**
     * This class is for setting and handling the background
     * randomizing process for saving code lines in the "Visitor session" screens
     * Picture links from my library: https://postimg.cc/gallery/21ccskP
     * This class is currently unused because of android API limits
     */
    private String[] linksArray = {
        // TODO: Add more images (v1.1)
        "https://lh3.googleusercontent.com/2u89wG2QNxGLdTcYqVR2rFQ2nZNVX2_Ea0E_yvtIAT_JeQWB2bA8I4X7xhkBzi9jg1Yi9KcF_dXdBnLPblkwIqC155nCUzJAKFPOVHtxYbP3O0Qj0L9yQ-NKaVM9XT6f4321Oc-qhu1CsxdaX06QXoicMYt70ARJnSqsxdIdYZ5KTfBIgH9LWrtw8qPpOsDl1Gca_rpCMQ80x1IDYayvAUKOZIrHdDDzH64qz1A-XTCUXqHxWpNy2ZTkFpQPIc_XPCAnDgRoKH9PKcSrUfob37kjNJBwuDKZBXxH4ZW7aFOg3EhSGB8hTTXD9Q6MEPSzUkGYiyuXpLNI3EJ1IpdabIHMJeXXHldb9QPoLcoC2Wl_QqBrFMlluydK0_PnH5su9eLtyN6CONSD1l1Nmpqi3ulbeJP5d-Nw0VHD1aNe7ZqUTwCeqI0IDp9fdvEiEQHSwFGt9ehyBat5D7Um6VFXMupptySYMogyKbchaETczeqBfXmJMx_nDulCfPGwrM5y3WBh3f-bp--_7lFDJzOx0KeY9Qg6NUqbxeaUtCY3QBgBsHNXwROjK010X6phH3HBAH38fPM3dWbFJnM6Cg34mwQZNxRWseASJFx1E4_nVUycpv5y7ZWgO5jmpucAeNdMcZ1m6guY29HDwJfE3EvgFGlws8W3DL02SfwQtvJXZWn74QzEeRw2Dg5LAkYB=s969-no?authuser=0",
        "https://lh3.googleusercontent.com/DkmSW0-uyEhC1wV0PBq0MV32fL4I7VrGjpQWARaBxM4arSLkdLRdBA8HLveUiTgtRzUm24Qfcz4clMYZf6Vej8lnidzrOUD37qQaecojmpgfxcljj0ZtFluifkxpw_7hG1zgjl15OarHMjsWqLb9Q5cbSg73TtZEsuvb4QsZe8V8lAw6IxD6-1ZYhpmthOqIMS3d1980EGMEYGNygKwMWlHvwXoNLusoXxlXe1K0l8Ex7dDbsN_xTyL5QTKDOuQisPKAMoG7k74SA5DjxtK1teLtVWfaTJ71BU8i099EjdXkuIcqOGCWdzZp77BR6g83t3B9pUdGJHV5e7jqsatsbNKyeSIJHBgQKXmO0dPCsUsHvXqe9ol0KJ9MpWVKwnZBeVGyI_bAZ3Kniv8wxLJMNx1ZhnsTqEJ6eCG7IDAzWrL9UHP83hQxNuwxN4WfofumKJ9Wr2WytS-dv4kK2amqRZa6xccUzy2dYmra41mwSGk55rhJcvYd5tNI2uUpNzTGjr18G28Vm-2nL4TcpHla4psT2uTVOEjTEcmQqxl_Z31Qqw_U9LbdQWEgtDDjxZNh9TDdyNbnG1LewkbPiN1_JrRmC-sWB8ds53H9DSonX5fe-CUroXQK77yKZKsXYB9FtLSPOK3Q599WqqtnUJgcKY3B4uPJUul8lzI-MgGHwbCnsKaODMpR7ZdGG5bY=w1100-h970-no?authuser=0",
        "https://lh3.googleusercontent.com/jZxRMTNEYH1cOlo79oP9tYTBPKaDbJkqe2vWRIhNzl4xxzWNWhvSQpITPPKvU0V6Oz24TqqqZoWt-k05qrXRnbtHwtSGL6IzBLeDpCU4wYCD8NH5ltlHV1KMCEm_MR9ENtQh0hMXgPNCxVvuKtTY7EYnbneGJ1FIVtSkPZH4fMZf0r0sHzGRJ4dHkGTFUmUYZqnXEdX_hyOsbH42Rp62Vr0ZU8VVWKnPUQyYYpy7rVi5XPw6mJ_U86SXy595-zbgZPm-bIW38frlyVvxDKDhJLYILE7CCeZp5SfSHqUAqaaH6iEjs3coqlm89TU_7S807Uc4xBElaYSE9_dOLqnouqlNlVwBG8e9sZU3XRu51PKF0hV-cIJQSUD1mt1hb0MrlTENr6sm9U7ILLSfz6BPXHBn6SieIRGFBeQulK1up0osXM5aL8vb_P4jjBXNu-X0jdedsqZ7FvKHFrYP1Pj94pQZMQQffp5PsXXU0edJB3IhugOMC3Cs8Htn5XLCSRInOegHmnxAsSUi26siNNSlLQoBmGHGlQwsQ389W_laSNbmjuB9SeyNLgM9ymN18yM8mgLPPdse3mNAYxNUG7nLy58gLvCvACoeFABDsgy-8bDybZ3F7KkqrUfzt2SsMifynBJubr-GxjIeF_z8cOm0qcI-7i6RNdtVwpKJVPQElwV9l4-kfrcMvKk9Rihc=w1454-h969-no?authuser=0"
    };

    public BackgroundHandling() {
        // Empty constructor
    }

    public String getRandomPicture() {
        // Returns a link of a background picture
        int index = new Random().nextInt(this.linksArray.length);
        return this.linksArray[index];
    }
}
