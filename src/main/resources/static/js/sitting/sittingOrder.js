// 수정 기능
const modifyButton = document.getElementById('modify-btn');
const applySitterButton = document.getElementById('apply-sitter-btn');
const deleteButton = document.getElementById('delete-btn');
const memberEmail = document.getElementById('email').value;
const userEmail = document.getElementById('userEmail').value;

if(memberEmail == userEmail){
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
            if(memberEmail == userEmail){
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
        if(memberEmail == userEmail){
            let url = window.location.href;
                    let id = url.charAt(url.length - 1);

                    fetch(`/sitting-order/detail/${id}`, {
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
                            location.replace(`/sitting-order/detail/${id}`);
                        });
        } else {
            alert("작성자만 수정할 수 있습니다")
        }

    });
}


// 삭제 기능
if (deleteButton) {

        deleteButton.addEventListener('click', event => {
            if(memberEmail == userEmail){
                let id = document.getElementById('orderId').value;
                fetch(`/sitting-order/detail/${id}`, {
                    method: 'DELETE'
                })
                    .then(() => {
                        alert('삭제가 완료되었습니다.');
                        location.replace('/sitting-orders');
                    });
                } else {
                        alert("작성자만 삭제가 가능합니다");
                    }
            });

}

//돌봄 신청
if(applySitterButton){
    applySitterButton.addEventListener('click', event =>{
        if(memberEmail != userEmail){
            let url = window.location.href;
            let id = url.charAt(url.length - 1);
            fetch(`/sitting-order/sitter`, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    email: userEmail,
                    orderId: document.getElementById('orderId').value
                })
            })
                .then(() => {
                    alert('돌본 신청 됐습니다');
                    location.replace('/mypage')
                });
        }
    });
}
