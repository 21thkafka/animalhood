// 수정 기능
const modifyButton = document.getElementById('modify-btn');

console.log("진입전");
if (modifyButton){
    console.log("modifyButton true")
    var detail = document.getElementById("detailTable");
    var update = document.getElementById("updateForm");
    modifyButton.addEventListener('click', event => {
        detail.style.display = 'none';
        update.style.display = 'block';
    });
}

/*if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(() => {
                alert('수정이 완료되었습니다.');
                location.replace(`/articles/${id}`);
            });
    });
}*/