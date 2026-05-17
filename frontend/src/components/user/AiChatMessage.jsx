const Array = [
    {
        from: 'user',
        message: 'Xin chào, tôi cần giúp đỡ về sản phẩm của bạn.'
    },
    {
        from: 'ai',
        message: 'Chào bạn! Tôi có thể giúp gì cho bạn về sản phẩm của chúng tôi?'
    },
    {
        from: 'user',
        message: 'Sản phẩm của bạn có những tính năng gì nổi bật?'
    },
    {
        from: 'ai',
        message: 'Sản phẩm của chúng tôi có nhiều tính năng nổi bật như hiệu suất cao, thiết kế đẹp mắt và giá cả hợp lý. Bạn có muốn biết thêm chi tiết về một tính năng cụ thể nào không?'
    },
    {
        from: 'user',
        message: 'Tôi muốn biết về hiệu suất của sản phẩm.'
    },
    {
        from: 'ai',
        message: 'Sản phẩm của chúng tôi được trang bị công nghệ tiên tiến giúp tăng hiệu suất làm việc lên đến 30% so với các sản phẩm cùng loại trên thị trường. Bạn có muốn xem một số đánh giá từ khách hàng đã sử dụng sản phẩm không?'
    },
]

export default function AiChatMessage({from, message }) {
    return (
        Array.map((item, index) => (
            <div key={index} className={`chat-message ${item.from === 'user' ? 'right' : 'left'}`}
            >
                <div className={`${item.from === 'user' ? 'user-message' : 'ai-message'}`}>
                    {item.message}
                </div>
            </div>
        ))
    );
}