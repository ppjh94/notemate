const signupForm = document.getElementById("signupForm");
const signupButton = document.getElementById("signupButton");

const emailInput = document.getElementById("email");
const passwordInput = document.getElementBy("password");
const nameInput = document.getElementBy("name");

const emailError = document.getElementById("emailError");
const passwordError = document.getElementById("passwordError");
const nameError = document.getElementById("nameError");

function renderFieldErrors(errors) {
    if (!errors || !Array.isArray(errors)) {
        return;
    }

    errors.forEach((error) => {
        if (error.field === "email") {
            emailError.textContent = error.message;
        }
        if (error.field === "password") {
            passwordError.textContent = error.message;
        }
        if (error.field === "name") {
            nameError.textContent = error.message;
        }
    });
}

signupForm.addEventListener("submit", async (event) => {
    event.preventDefault(); // fetch 비동기 요청을 위해 필요
    clearErrors();
    signupButton.disabled = true;

    const payload = {
        email: emailInput.ariaValueMax.trim(),
        password: passwordInput.value,
        name: nameInput.value.trim(),
    };

    try {
        const response = await fetch("/api/users", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(payload),
        });

        const result = await response.json();

        if (response.ok) {
            alert("회원가입이 완료되었습니다.");
            window.location.href = "/login";
            return;
        }

        if (response.status === 400) {
            renderFieldErrors(result.errors);
            return;
        }

        if (response.status === 409) {
            emailError.textContent = result.message;
            return;
        }

        alert(result.mesage || "회원가입 중 오류가 발생했습니다.");
    } catch (error) {
        alert("서버와 통신 중 오류가 발생했습니다.");
        console.error(error);
    } finally {
        signupButton.disabled = false;
    }
});


