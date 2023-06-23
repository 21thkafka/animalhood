// 수정 기능
const modifyButton = document.getElementById('modify-btn');
const applySitterButton = document.getElementById('apply-sitter-btn');
const deleteButton = document.getElementById('delete-btn');
const memberId = document.getElementById('email').value;
const userEmail = document.getElementById('userEmail').value;

if(memberId == userEmail){
    applySitterButton.style.display = 'none';
} else {
    modifyButton.style.display = 'none';
    deleteButton.style.display = 'none';
}

if (modifyButton){
    console.log("modifyButton true")
        var detail = document.getElementById("detailTable");
        var update = document.getElementById("updateForm");
        modifyButton.addEventListener('click', event => {
            if(memberId == userEmail){
                detail.style.display = 'none';
                update.style.display = 'block';
            } else {
                alert("작성자가 아닙니다");
            }
        });


}

const updateButton = document.getElementById('update-btn');

if (updateButton) {
    updateButton.addEventListener('click', event => {
        if(memberId == userEmail){
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
        } else {
            alert("작성자만 수정할 수 있습니다")
        }

    });
}


// 삭제 기능
if (deleteButton) {

        deleteButton.addEventListener('click', event => {
            if(memberId == userEmail){
                let id = document.getElementById('orderId').value;
                fetch(`/sittingOrder/detail/${id}`, {
                    method: 'DELETE'
                })
                    .then(() => {
                        alert('삭제가 완료되었습니다.');
                        location.replace('/sittingOrders');
                    });
                } else {
                        alert("작성자만 삭제가 가능합니다");
                    }
            });

}

