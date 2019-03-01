# Quản lý băng đĩa
Bài tập lớn Lập trình hướng sự kiện với Công nghệ Java

## Thành viên nhóm:
1. Nguyễn Chí Cường
2. Đặng Lê Minh Trường
3. Phạm Minh Khoa

## Đề bài

> Công ty X cần thiết kế chương trình quản lý cho thuê băng đĩa trên địa bàn quận 1, TP.HCM. Quản lý thông tin băng đĩa được thực hiện: thường xuyên nhập thông tin thêm các băng đĩa dựa vào việc chọn lựa băng đĩa từ danh mục mà các nhà cung cấp gửi tới. Khi các băng đĩa hỏng thì xoá bỏ thông tin. Có thể sửa đổi thông tin về băng đĩa khi cần thiết. Thông tin về băng đĩa bao gồm: mã băng đĩa, tên băng đĩa, thể loại, tình trạng, hãng sản xuất, các ghi chú, .... 

> Chỉ có thành viên của hệ thống mới được thuê băng đĩa. Khách hàng lần đầu đến thuê sẽ được nhập thông tin như họ tên, giới tính, điện thoại, địa chỉ, ... và các đặc điểm khác để xác nhận (số chứng minh nhân dân/hay số hộ chiếu). Sau khi xác nhận các thông tin chính xác với khách hàng, hệ thống tạo ngay một thẻ thành viên gửi cho khách. Trên thẻ có ghi rõ họ tên thành viên, địa chỉ, mã thẻ, ngày hết hạn (thông thường thẻ có giá trị trong vòng 3 năm kể từ ngày làm thẻ). Mỗi một khách hàng tại cùng 1 thời điểm chỉ có 1 thẻ thành viên, nếu khách hàng mất thẻ thành viên có thể quay lại cửa hàng khai báo để cấp lại thẻ. 

> Khi thuê băng đĩa tại công ty X, khách hàng có thể thuê một số lượng bất kỳ tuy nhiên nhân viên phải kiểm tra thông tin nếu khách hàng có băng đĩa thuê trễ hạn. Nếu khách hàng thành viên không có băng đĩa trễ hạn thì sẽ được lập phiếu thuê, thông tin trong phiếu thuê gồm: số phiếu, ngày thuê, mã thẻ thành viên và các thông tin về băng đĩa gồm: mã băng đĩa, tên băng đĩa, thể loại, tình trạng, số lượng, số ngày được mượn và đơn giá.

> Khi khách hàng trả băng đĩa thì nhân viên cửa hàng sẽ kiểm tra tình trạng băng đĩa trả và ghi nhận về việc trả băng đĩa của khách. Nếu khách trả muộn so với ngày quy định trên phiếu cho thuê thì họ phải chịu một khoản tiền phạt là 50% tiền thuê/băng đĩa. Mỗi lần trả khách hàng thành viên có thể trả hết hoặc chỉ 1 phần trong danh sách băng đĩa trong lần thuê trước.

> Để theo dõi và quản lý nhân viên làm việc, công ty thực hiện thêm mới vào danh sách khi có nhân viên mới được tuyển, sửa đổi thông tin khi có những biến đổi xảy ra và xoá bỏ nhân viên khi hết hợp đồng hoặc bị sa thải. Các thông tin về nhân viên gồm: Mã nhân viên, tên nhân viên, điện thoại liên hệ, các mô tả khác. Ngoài ra để tiện theo dõi việc kinh doanh của công ty, hàng tuần công ty lập các báo cáo gửi cho ban giám đốc về doanh thu trong tuần, danh sách các băng đĩa quá hạn, báo cáo về băng đĩa đang được yêu thích để ban giám đốc có các biện pháp điều chỉnh.

- Yêu cầu: 
  * Phân tích, thiết kế ứng dụng quản lý thông tin cho công ty du lịch với các đặc tả ban đầu như trên, các đặc tả khác có thể mô tả thêm chi tiết. 
  * Về phần thực hiện chương trình ứng dụng theo phân tích, thiết kế: 
  * Tối thiểu chương trình bao gồm các chức năng chính: Thêm, xóa, cập nhật, liệt kê (dạng danh sách và chi tiết), tìm kiếm (đơn giản, nâng cao) dữ liệu của các bảng (lưu ý cập nhật, xóa dữ liệu của các bảng có quan hệ).
  * Giao diện thân thiện, sử dụng các phím tắt, tab khi cần thiết.
  * Phần viết code cần phải dùng Coding Convention chung cho các ngôn ngữ (Java/C#).
