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

const updateButton = document.getElementById('update-btn');

if (updateButton) {
    updateButton.addEventListener('click', event => {
        let url = window.location.href;
        let id = url.charAt(url.length - 1);

        fetch(`/sittingOrder/detail/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                startDate: document.getElementById('startDate').value,
                endDate: document.getElementById('endDate').value,
                detail: document.getElementById('detail').value
            })
        })
            .then(() => {
                alert('수정이 완료되었습니다.');
                location.replace(`/sittingOrder/detail/${id}`);
            });
    });
}


// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('orderId').value;
        fetch(`/sittingOrder/detail/${id}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/sittingOrders');
            });
    });
}