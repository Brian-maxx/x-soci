const Array = [
    {
        id: 1,
        title: "Hôm nay thời tiết thế nào?",
    },
    {
        id: 2,
        title: "Tư vấn mua laptop chơi game",
    },
    {
        id: 3,
        title: "Học đại học có khó không?",
    },
    {
        id: 4,
        title: "Hỗ trợ giải bài tập toán lớp 10",
    },
    {
        id: 5,
        title: "Tôi muốn học lập trình, bắt đầu từ đâu?",
    },
    {
        id: 6,
        title: "Học tiếng Anh, có lời khuyên nào không?",
    },
    {
        id: 7,
        title: "Kỹ thuật sử dụng AI trong kinh doanh",
    },
    {
        id: 8,
        title: "Tìm gia sư thế nào hiệu quả?",
    }
]
export default function AiChatHistory() {
    return (
        <div className="chat-history-list">
            {Array.map((item) => (
                <div key={item.id} className="chat-history-item">
                    <span className="chat-history-title">{item.title}</span>
                </div>
            ))}
        </div>
    )
}