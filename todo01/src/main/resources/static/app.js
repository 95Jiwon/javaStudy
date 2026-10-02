
    function submitButtonOnClick(){
        const writer = document.querySelector(".writer");
        const date = document.querySelector(".date");
        const content = document.querySelector(".content");

        const writerValue = writer.value;
        const dateValue = date.value;
        const contentValue = content.value;

        const todoData = {
            "writer" : writerValue,
            "date" : dateValue,
            "content" : contentValue,
        }

        console.log(todoData); //console.log = JS에서는 println

        fetch("http://localhost:8080/api/todos", {
            method: "post",
            body: JSON.stringify(todoData),
            headers: {
                "Content-Type" : "application/json",
            }
        });

    }

    function main(){
        const submitButton = document.querySelector(".submit-button");
        submitButton.onclick = submitButtonOnClick;
    }

    main();