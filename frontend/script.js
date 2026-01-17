const taskInput = document.getElementById("taskInput");
const addBtn = document.getElementById("addBtn");
const todoList = document.getElementById("todoList");

let tasks = [];

function renderTasks() {
    todoList.innerHTML = "";

    tasks.forEach((task, index) => {
        const li = document.createElement("li");
        if (task.completed) li.classList.add("completed");

        const span = document.createElement("span");
        span.textContent = task.text;

        const actions = document.createElement("div");
        actions.className = "actions";

        const completeBtn = document.createElement("button");
        completeBtn.textContent = "✔️";
        completeBtn.onclick = () => {
            task.completed = !task.completed;
            renderTasks();
        };

        const deleteBtn = document.createElement("button");
        deleteBtn.textContent = "❌";
        deleteBtn.onclick = () => {
            tasks.splice(index, 1);
            renderTasks();
        };

        actions.appendChild(completeBtn);
        actions.appendChild(deleteBtn);

        li.appendChild(span);
        li.appendChild(actions);
        todoList.appendChild(li);
    });
}

addBtn.addEventListener("click", () => {
    const text = taskInput.value.trim();
    if (text === "") return;

    // 🔥 SEND TO JAVA BACKEND
    fetch("http://localhost:8081/add", {
        method: "POST",
        headers: {
            "Content-Type": "text/plain"
        },
        body: text
    })
    .then(response => response.text())
    .then(data => {
        console.log(data);

        // update UI after backend success
        tasks.push({ text: text, completed: false });
        renderTasks();
        taskInput.value = "";
    })
    .catch(error => {
        console.error("Error: - script.js:65", error);
        alert("Backend not reachable");
    });
});

renderTasks();